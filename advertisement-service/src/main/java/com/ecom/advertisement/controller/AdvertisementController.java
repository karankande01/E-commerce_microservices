package com.ecom.advertisement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.advertisement.dto.AdvertisementDto;
import com.ecom.advertisement.dto.AdvertisementListResponse;
import com.ecom.advertisement.dto.AdvertisementResponse;
import com.ecom.advertisement.entity.Advertisement;
import com.ecom.advertisement.service.AdvertisementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/advertisement")
@Validated
public class AdvertisementController {
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@PostMapping("")
	public ResponseEntity<AdvertisementResponse> createAdvertisement(@Valid @org.springframework.web.bind.annotation.RequestBody AdvertisementDto advertisementDto){
		AdvertisementResponse advertisementResponse = advertisementService.createAdvertisement(advertisementDto);
		return ResponseEntity.ok(advertisementResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AdvertisementResponse> getAdvertisementById(@PathVariable Long id){
		AdvertisementResponse advertisementResponse = advertisementService.getAdvertisementById(id);
		return ResponseEntity.ok(advertisementResponse);
	}
	
	@GetMapping("")
	public ResponseEntity<AdvertisementListResponse> getAllAdvertisement(){
		AdvertisementListResponse advertisementListResponse = advertisementService.getAllAdvertisement();
		return ResponseEntity.ok(advertisementListResponse);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AdvertisementResponse> updateAdvertisement(@Valid @org.springframework.web.bind.annotation.RequestBody AdvertisementDto advertisementDto,@PathVariable Long id){
		AdvertisementResponse advertisementResponse = advertisementService.updateAdvertisement(id, advertisementDto);
		return ResponseEntity.ok(advertisementResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AdvertisementResponse> deleteAdvertisement(@PathVariable Long id){
		AdvertisementResponse advertisementResponse = advertisementService.deleteAdvertisement(id);
		return ResponseEntity.ok(advertisementResponse);
	}
	
	 @GetMapping("/page")
	    public List<Advertisement> getAdvertisementWithPaging(
	            @RequestParam(defaultValue = "0") int pageNo,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(required = false) List<String> sortFields,
	            @RequestParam(required = false) String searchKeyword) {

	        return advertisementService.getAdvertisementByPagination(pageNo, pageSize, sortFields,searchKeyword);
	    } 

}
