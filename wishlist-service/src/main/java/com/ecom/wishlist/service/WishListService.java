package com.ecom.wishlist.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecom.wishlist.constants.EcomConstant;
import com.ecom.wishlist.dto.WishListDto;
import com.ecom.wishlist.dto.WishListListResponse;
import com.ecom.wishlist.dto.WishListResponse;
import com.ecom.wishlist.entity.WishList;
import com.ecom.wishlist.mapper.ResponseMetaDataMapper;
import com.ecom.wishlist.mapper.WishListMapper;
import com.ecom.wishlist.repository.WishlistRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WishListService {
	
	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private WishListMapper wishListMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public WishListResponse createWishlist(WishListDto wishListDto) {
		
		System.out.println(wishListDto.getUsersId());
		
	WishList wishList =	wishListMapper.map(wishListDto);
	wishList = wishlistRepository.save(wishList);
	
	WishListResponse wishListResponse = WishListResponse.builder()
			.data(wishListMapper.map(wishList))
			.meta(metaDataMapper.map(EcomConstant.WISHLIST_CREATE_SUCCESS_CODE, EcomConstant.WISHLIST_CREATE_SUCCESS_MESSAGE))
			.build();
	
	return wishListResponse;
		
	}
	
	public WishListResponse getWishlistById (Long id) {
		
		Optional<WishList> wishList = wishlistRepository.findById(id);
		
		if(wishList.isPresent()) {
			
		}
		
		WishList wishList2 = wishList.get();
		
		WishListResponse wishListResponse = WishListResponse.builder()
				.data(wishListMapper.map(wishList2))
				.meta(metaDataMapper.map(EcomConstant.WISHLIST_FETCH_CODE,EcomConstant.WISHLIST_FETCH_MESSAGE))
				.build();
		
		return wishListResponse;
	}

	public WishListListResponse getAllWishlist() {
		List<WishList> wishList = wishlistRepository.findAll();
		
		WishListListResponse wishListListResponse = WishListListResponse.builder()
				.data(wishListMapper.map(wishList))
				.meta(metaDataMapper.map(EcomConstant.WISHLIST_FETCH_CODE, EcomConstant.WISHLIST_FETCH_MESSAGE))
				.build();
		
		return wishListListResponse;
	}
	
	public WishListResponse updateWishlist(WishListDto wishListDto,Long id) {
		
		Optional<WishList> wishList = wishlistRepository.findById(id);
		
		if(wishList.isPresent()) {
			
		}
		
		WishList wishList2 = wishList.get();
		wishList2 = wishListMapper.map(wishListDto);
	    wishList2 =	wishlistRepository.save(wishList2);
	    
	    WishListResponse wishListResponse = WishListResponse.builder()
	    		.data(wishListMapper.map(wishList2))
	    		.meta(metaDataMapper.map(EcomConstant.WISHLIST_UPDATE_CODE, EcomConstant.WISHLIST_UPDATE_MESSAGE))
	    		.build();
	    
	    return wishListResponse;
	}
	
	public WishListResponse deleteWishlist(Long id) {
		Optional<WishList> wishList = wishlistRepository.findById(id);
		
		if(!wishList.isPresent()) {
			
		}
		wishlistRepository.delete(wishList.get());
		
	           	 return WishListResponse.builder()
				.meta(metaDataMapper.map(EcomConstant.WISHLIST_DELETE_CODE, EcomConstant.WISHLIST_DELETE_MESSAGE))
				.build();
		
	}
	
//	public List<WishList> getWishlistByPagination(int pageNo, int pageSize){
//		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
//		Page<WishList> pagingWishlist = wishlistRepository.findAll(pageRequest);
//		return pagingWishlist.getContent();
//	}
	
	public List<WishList> getWishListByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<WishList> specification = (root, query, criteriaBuilder) -> {
	    	
	        List<Predicate> predicates = new ArrayList<>();

	     // Add predicates for each field and each search term
	        for (String term : searchTerms) {
	            List<Predicate> fieldPredicates = new ArrayList<>();

	            // Add predicates for each field
	            for (String field : fields) {
	                fieldPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + term.toLowerCase() + "%"));
	            }

	            // Combine field predicates with OR
	            predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
	        }

	        // Combine the term predicates with AND
	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    };

	    Page<WishList> pagingUser = wishlistRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	

	
	
	
	
	
	





}
