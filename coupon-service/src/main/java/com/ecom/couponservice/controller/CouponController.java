package com.ecom.couponservice.controller;

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

import com.ecom.couponservice.dto.CouponDto;
import com.ecom.couponservice.dto.CouponListResponse;
import com.ecom.couponservice.dto.CouponResponse;
import com.ecom.couponservice.entity.Coupon;
import com.ecom.couponservice.service.CouponService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/coupon")
@Validated
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@PostMapping
	public ResponseEntity<CouponResponse> createCoupon(@Valid @org.springframework.web.bind.annotation.RequestBody CouponDto couponDto){
		CouponResponse couponResponse = couponService.createCoupon(couponDto);
		return ResponseEntity.ok(couponResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CouponResponse> getCouponById(@PathVariable Long id){
		CouponResponse couponResponse = couponService.getCouponById(id);
		return ResponseEntity.ok(couponResponse);
	}
	
	@GetMapping
	public ResponseEntity<CouponListResponse> getAllCartsItems(){
		CouponListResponse couponListResponse = couponService.getAllCoupon();
		return ResponseEntity.ok(couponListResponse);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CouponResponse> updateCoupon(@Valid @org.springframework.web.bind.annotation.RequestBody CouponDto couponDto,@PathVariable Long id){
		CouponResponse couponResponse = couponService.updateCoupon(id, couponDto);
		return ResponseEntity.ok(couponResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CouponResponse> deleteCoupon(@PathVariable Long id){
		CouponResponse couponResponse = couponService.deleteCoupon(id);
		return ResponseEntity.ok(couponResponse);
	}
	

	 @GetMapping("/page")
	    public List<Coupon> getCouponWithPaging(
	            @RequestParam(defaultValue = "0") int pageNo,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(required = false) List<String> sortFields,
	            @RequestParam(required = false) String searchKeyword) {

	        return couponService.getCouponByPagination(pageNo, pageSize, sortFields,searchKeyword);
	    } 

}
