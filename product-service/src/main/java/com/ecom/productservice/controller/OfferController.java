package com.ecom.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.productservice.dto.OfferDto;
import com.ecom.productservice.dto.OfferListResponse;
import com.ecom.productservice.dto.OfferResponse;
import com.ecom.productservice.entity.Offer;
import com.ecom.productservice.service.OfferService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/offer")
@Validated
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	
	@PostMapping
	public ResponseEntity<OfferResponse> createOffer(@Valid @RequestBody OfferDto offerDto){
		OfferResponse offerResponse = offerService.createOffer(offerDto);
		return ResponseEntity.ok(offerResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OfferResponse> getOfferById(@PathVariable Long id) {
		OfferResponse offerResponse = offerService.getOfferById(id);
		return ResponseEntity.ok(offerResponse);
	}
	
	@GetMapping
	public ResponseEntity<OfferListResponse> getAllOffer(){
		OfferListResponse offerListResponse = offerService.getAllOffer();
		return ResponseEntity.ok(offerListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OfferResponse> updateOffer(@PathVariable Long id,@Valid @RequestBody OfferDto offerDto){
		OfferResponse offerResponse = offerService.updateOffer(id, offerDto);
		return ResponseEntity.ok(offerResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OfferResponse> deleteOffer(@PathVariable Long id) {
		OfferResponse offerResponse = offerService.deleteOffer(id);
		return ResponseEntity.ok(offerResponse);
	}
	
	@GetMapping("/page")
    public List<Offer> getOfferWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return offerService.getOfferByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  

}
