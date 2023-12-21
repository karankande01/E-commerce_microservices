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
import com.ecom.productservice.dto.OrderDto;
import com.ecom.productservice.dto.OrderListResponse;
import com.ecom.productservice.dto.OrderResponse;
import com.ecom.productservice.entity.Order;
import com.ecom.productservice.mapper.OrderMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.repository.OrderRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public OrderResponse createOrder (OrderDto orderDto) {
		
		Order order = orderMapper.map(orderDto);
	    order =	orderRepository.save(order);
	    
	    OrderResponse orderResponse = OrderResponse.builder()
	    		.data(orderMapper.map(order))
	    		.meta(metaDataMapper.map(ProductsConstants.ORDER_SERVICE_SUCCESS_CODE, ProductsConstants.ORDER_SERVICE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return orderResponse;
	}
	
	public OrderResponse getOrderById (Long id) {
		
		Optional<Order> order = orderRepository.findById(id);
		
		if(!order.isPresent()) {
			
		}
		Order order2 = order.get();
		OrderResponse orderResponse = OrderResponse.builder()
				.data(orderMapper.map(order2))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_SERVICE_FETCH_CODE, ProductsConstants.ORDER_SERVICE_FETCH_MESSAGE))
				.build();
		
		return orderResponse;
	}
	
	public OrderListResponse getAllOrder() {
		List<Order> orders = orderRepository.findAll();
		
		OrderListResponse orderListResponse = OrderListResponse.builder()
				.data(orderMapper.map(orders))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_SERVICE_FETCH_CODE, ProductsConstants.ORDER_SERVICE_FETCH_MESSAGE))
				.build();
		
		return orderListResponse;
	}
	
	public OrderResponse updateOrder (Long id, OrderDto orderDto) {
		Optional<Order> order = orderRepository.findById(id);
		
		if(!order.isPresent()) {
			
		}
		
		Order order2 = order.get();
		order2 = orderMapper.map(orderDto);
		order2 = orderRepository.save(order2);
		
		OrderResponse orderResponse =OrderResponse.builder()
				.data(orderMapper.map(order2))
				.meta(metaDataMapper.map(ProductsConstants.ORDER_SERVICE_UPDATE_CODE, ProductsConstants.ORDER_SERVICE_UPDATE_MESSAGE))
				.build();
		
		return orderResponse;
	}
	
	public OrderResponse deleteOrder (Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if(!order.isPresent()) {
			
		}
		
		orderRepository.delete(order.get());
		 
		 return OrderResponse.builder()
				 .meta(metaDataMapper.map(ProductsConstants.ORDER_SERVICE__DELETE_SUCCESS_CODE, ProductsConstants.ORDER_SERVICE_DELETE_SUCCESS_MESSAGE))
				 .build();
	}
	
	public List<Order> getOrderByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<Order> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<Order> pagingUser = orderRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	
}
	
