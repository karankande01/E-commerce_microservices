package com.ecom.cart.service;

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

import com.ecom.cart.constants.EcomConstant;
import com.ecom.cart.dto.CartItemsDto;
import com.ecom.cart.dto.CartItemsListResponse;
import com.ecom.cart.dto.CartItemsResponse;
import com.ecom.cart.entity.CartItems;
import com.ecom.cart.mapper.CartItemsMapper;
import com.ecom.cart.mapper.ResponseMetaDataMapper;
import com.ecom.cart.repository.CartItemsRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemsService {
	
	@Autowired
	private CartItemsRepository cartItemsRepository;
	
	@Autowired
	private CartItemsMapper cartItemsMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public CartItemsResponse createCart (CartItemsDto cartItemsDto) {
		
		CartItems cartItems = cartItemsMapper.map(cartItemsDto);
	    System.out.println("Mapped CartItems entity: " + cartItems);
		cartItems = cartItemsRepository.save(cartItems);
	    System.out.println("Saved CartItems entity: " + cartItems);

		CartItemsResponse cartItemsResponse = CartItemsResponse.builder()
				.data(cartItemsMapper.map(cartItems))
				.meta(metaDataMapper.map(EcomConstant.CARTITEMS_CREATE_SUCCESS_CODE, EcomConstant.CARTITEMS_CREATE_SUCCESS_MESSAGE))
				.build();
		
		return cartItemsResponse;
	}
	
	public CartItemsResponse getCartItemsById(Long id) {
		Optional<CartItems> cartItems = cartItemsRepository.findById(id);
		if(cartItems.isPresent()) {
			
		}
		CartItems cartItems2 = cartItems.get();
		CartItemsResponse cartItemsResponse = CartItemsResponse.builder()
				.data(cartItemsMapper.map(cartItems2))
				.meta(metaDataMapper.map(EcomConstant.CARTITEMS_FETCH_CODE, EcomConstant.CARTITEMS_FETCH_MESSAGE))
				.build();
		
		return cartItemsResponse;
	} 
	
	public CartItemsListResponse getAllCartItems() {
		List<CartItems> cartItems = cartItemsRepository.findAll();
		
		CartItemsListResponse cartItemsListResponse = CartItemsListResponse.builder()
				.data(cartItemsMapper.map(cartItems))
				.meta(metaDataMapper.map(EcomConstant.CARTITEMS_FETCH_CODE, EcomConstant.CARTITEMS_FETCH_MESSAGE))
				.build();
		
		return cartItemsListResponse;
	}
	
	public CartItemsResponse updateCartItems(Long id,CartItemsDto cartItemsDto) {
		
		Optional<CartItems> cartItems = cartItemsRepository.findById(id);
		
		if(cartItems.isPresent()) {
			
		}
		CartItems cartItems2 = cartItems.get();
		cartItems2 = cartItemsMapper.map(cartItemsDto);
		cartItems2 = cartItemsRepository.save(cartItems2);
		
		CartItemsResponse cartItemsResponse = CartItemsResponse.builder()
				.data(cartItemsMapper.map(cartItems2))
				.meta(metaDataMapper.map(EcomConstant.CARTITEMS_UPDATE_CODE, EcomConstant.CARTITEMS_UPDATE_MESSAGE))
				.build();
		
		return cartItemsResponse;
	}
	
	public CartItemsResponse deleteCartItems(Long id) {
		Optional<CartItems> cartItems = cartItemsRepository.findById(id);
		if(!cartItems.isPresent()) {
			
		}
		cartItemsRepository.delete(cartItems.get());
		
		return CartItemsResponse.builder()
				.meta(metaDataMapper.map(EcomConstant.CARTITEMS_DELETE_CODE, EcomConstant.CARTITEMS_DELETE_MESSAGE))
				.build();
	}
	
//	public List<CartItems> getCartItemByPagination(int pageNo, int pageSize) {
//
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
//        Page<CartItems> pagingUser = cartItemsRepository.findAll(pageRequest);
//        return pagingUser.getContent();
//    }
	
	public List<CartItems> getCartItemsByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<CartItems> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<CartItems> pagingUser = cartItemsRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
}
