package com.ecom.productservice.service;

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
import com.ecom.productservice.constant.ProductsConstants;
import com.ecom.productservice.dto.OrderItemDto;
import com.ecom.productservice.dto.OrderItemListResponse;
import com.ecom.productservice.dto.OrderItemResponse;
import com.ecom.productservice.entity.Order;
import com.ecom.productservice.entity.OrderItem;
import com.ecom.productservice.mapper.OrderItemMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.repository.OrderItemRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public OrderItemResponse createOrderItem (OrderItemDto orderItemDto) {
		
		OrderItem orderItem = orderItemMapper.map(orderItemDto);
	    orderItem =	orderItemRepository.save(orderItem);
	    
	    OrderItemResponse orderItemResponse = OrderItemResponse.builder()
	    		.data(orderItemMapper.map(orderItem))
	    		.meta(metaDataMapper.map(ProductsConstants.ORDER_ITEM_SERVICE_SUCCESS_CODE, ProductsConstants.ORDER_ITEM_SERVICE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return orderItemResponse;
	}
	
	public OrderItemResponse getOrderItemById (Long id) {
		
		Optional<OrderItem> orderItem = orderItemRepository.findById(id);
		
		if(!orderItem.isPresent()) {
			
		}
		OrderItem orderItem2 = orderItem.get();
		OrderItemResponse orderItemResponse = OrderItemResponse.builder()
				.data(orderItemMapper.map(orderItem2))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_ITEM_SERVICE_FETCH_CODE, ProductsConstants.ORDER_ITEM_SERVICE_FETCH_MESSAGE))
				.build();
		
		return orderItemResponse;
	}
	
	public OrderItemListResponse getAllOrderItem() {
		List<OrderItem> orderItems = orderItemRepository.findAll();
		
		OrderItemListResponse orderItemListResponse = OrderItemListResponse.builder()
				.data(orderItemMapper.map(orderItems))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_ITEM_SERVICE_FETCH_CODE, ProductsConstants.ORDER_ITEM_SERVICE_FETCH_MESSAGE))
				.build();
		
		return orderItemListResponse;
	}
	
	public OrderItemResponse updateOrderItem (Long id, OrderItemDto orderItemDto) {
		Optional<OrderItem> orderItem = orderItemRepository.findById(id);
		
		if(!orderItem.isPresent()) {
			
		}
		
		OrderItem orderItem2 = orderItem.get();
		orderItem2 = orderItemMapper.map(orderItemDto);
		orderItem2 = orderItemRepository.save(orderItem2);
		
		OrderItemResponse orderItemResponse =OrderItemResponse.builder()
				.data(orderItemMapper.map(orderItem2))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_ITEM_SERVICE_UPDATE_CODE, ProductsConstants.ORDER_ITEM_SERVICE_UPDATE_MESSAGE))
				.build();
		
		return orderItemResponse;
	}
	
	public OrderItemResponse deleteOrderItem (Long id) {
		Optional<OrderItem> orderItem = orderItemRepository.findById(id);
		if(!orderItem.isPresent()) {
			
		}
		
		 orderItemRepository.delete(orderItem.get());
		 
		 return OrderItemResponse.builder()
				 .meta(metaDataMapper.map(ProductsConstants.ORDER_ITEM_SERVICE__DELETE_SUCCESS_CODE, ProductsConstants.ORDER_ITEM_SERVICE_DELETE_SUCCESS_MESSAGE))
				 .build();
	}
	
	public List<OrderItem> getOrderItemByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<OrderItem> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<OrderItem> pagingUser = orderItemRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
