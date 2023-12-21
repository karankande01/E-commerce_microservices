package com.ecom.wishlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.wishlist.dto.WishListDto;
import com.ecom.wishlist.dto.WishListListResponse;
import com.ecom.wishlist.dto.WishListResponse;
import com.ecom.wishlist.entity.WishList;
import com.ecom.wishlist.service.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	
	@Autowired
	private WishListService wishListService;
	
	@PostMapping
	public ResponseEntity<WishListResponse> createWishlist(@RequestBody WishListDto wishListDto){
		System.out.println(wishListDto.getUsersId());
	 WishListResponse wishListResponse =	wishListService.createWishlist(wishListDto);
	 return ResponseEntity.ok(wishListResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WishListResponse> getWishlistById(@PathVariable Long id){
		WishListResponse wishListResponse = wishListService.getWishlistById(id);
		return ResponseEntity.ok(wishListResponse);
	}
	
	@GetMapping
	public ResponseEntity<WishListListResponse> getAllWishlist(){
		WishListListResponse wishListListResponse = wishListService.getAllWishlist();
		return ResponseEntity.ok(wishListListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WishListResponse> updateWishlist(@PathVariable Long id,@RequestBody WishListDto wishListDto){
		WishListResponse wishListResponse = wishListService.updateWishlist(wishListDto, id);
		return ResponseEntity.ok(wishListResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<WishListResponse> deleteWishlist(@PathVariable Long id){
		WishListResponse wishListResponse = wishListService.deleteWishlist(id);
		return ResponseEntity.ok(wishListResponse);
	}
	
//	@GetMapping("/page")
//	public List<WishList> getWishlistWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "10") Integer pageSize){
//            	
//            	return wishListService.getWishlistByPagination(pageNo, pageSize);
//            }
	
	@GetMapping("/page")
    public List<WishList> getWishListWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return wishListService.getWishListByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
