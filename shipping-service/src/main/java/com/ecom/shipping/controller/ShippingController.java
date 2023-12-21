package com.ecom.shipping.controller;

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

import com.ecom.shipping.dto.ShippingDto;
import com.ecom.shipping.dto.ShippingListResponse;
import com.ecom.shipping.dto.ShippingResponse;
import com.ecom.shipping.entity.Shipping;
import com.ecom.shipping.service.ShippingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shipping")
@Validated
public class ShippingController {
	
	@Autowired
	private ShippingService shippingService;
	
	@PostMapping
	public ResponseEntity<ShippingResponse> createShipping(@Valid @org.springframework.web.bind.annotation.RequestBody ShippingDto shippingDto){
		ShippingResponse shippingResponse = shippingService.createShipping(shippingDto);
		return ResponseEntity.ok(shippingResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShippingResponse> getShippingById(@PathVariable Long id){
		ShippingResponse shippingResponse = shippingService.getShippingById(id);
		return ResponseEntity.ok(shippingResponse);
	}
	
	@GetMapping
	public ResponseEntity<ShippingListResponse> getAllShipping(){
		ShippingListResponse shippingListResponse = shippingService.getAllShipping();
		return ResponseEntity.ok(shippingListResponse);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ShippingResponse> updateShipping(@Valid @org.springframework.web.bind.annotation.RequestBody ShippingDto shippingDto,@PathVariable Long id){
		ShippingResponse shippingResponse = shippingService.updateShipping(id, shippingDto);
		return ResponseEntity.ok(shippingResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ShippingResponse> deleteShipping(@PathVariable Long id){
		ShippingResponse shippingResponse = shippingService.deleteShipping(id);
		return ResponseEntity.ok(shippingResponse);
	}
	
	
	 @GetMapping("/page")
	    public List<Shipping> getShippingWithPaging(
	            @RequestParam(defaultValue = "0") int pageNo,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(required = false) List<String> sortFields,
	            @RequestParam(required = false) String searchKeyword) {

	        return shippingService.getShippingByPagination(pageNo, pageSize, sortFields,searchKeyword);
	    }  
	
	

}
