package com.ecom.cart.controller;

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

import com.ecom.cart.dto.CartItemsDto;
import com.ecom.cart.dto.CartItemsListResponse;
import com.ecom.cart.dto.CartItemsResponse;
import com.ecom.cart.entity.CartItems;
import com.ecom.cart.service.CartItemsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
@Validated
public class CartItemsController {
	
	@Autowired
	private CartItemsService cartItemsService;
	
	@PostMapping("")
	public ResponseEntity<CartItemsResponse> createCartItems(@Valid @RequestBody CartItemsDto cartItemsDto){
		System.out.println(cartItemsDto);
		CartItemsResponse cartItemsResponse = cartItemsService.createCart(cartItemsDto);
		return ResponseEntity.ok(cartItemsResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CartItemsResponse> getCartItemsById(@PathVariable Long id){
		CartItemsResponse cartItemsResponse = cartItemsService.getCartItemsById(id);
		return ResponseEntity.ok(cartItemsResponse);
	}
	
	@GetMapping("")
	public ResponseEntity<CartItemsListResponse> getAllCartsItems(){
		CartItemsListResponse cartItemsListResponse = cartItemsService.getAllCartItems();
		return ResponseEntity.ok(cartItemsListResponse);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartItemsResponse> updateCartItems(@Valid @org.springframework.web.bind.annotation.RequestBody CartItemsDto cartItemsDto,@PathVariable Long id){
		CartItemsResponse cartItemsResponse = cartItemsService.updateCartItems(id, cartItemsDto);
		return ResponseEntity.ok(cartItemsResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CartItemsResponse> deleteCartItems(@PathVariable Long id){
		CartItemsResponse cartItemsResponse = cartItemsService.deleteCartItems(id);
		return ResponseEntity.ok(cartItemsResponse);
	}
	
//	@GetMapping("/page")
//	public List<CartItems> getCartItemsWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "10") Integer pageSize){
//		
//		return cartItemsService.getCartItemByPagination(pageNo, pageSize);
//	}
	
	 @GetMapping("/page")
	    public List<CartItems> getCartItemsWithPaging(
	            @RequestParam(defaultValue = "0") int pageNo,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(required = false) List<String> sortFields,
	            @RequestParam(required = false) String searchKeyword) {

	        return cartItemsService.getCartItemsByPagination(pageNo, pageSize, sortFields,searchKeyword);
	    }  
}
