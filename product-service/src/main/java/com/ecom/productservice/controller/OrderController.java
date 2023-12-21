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

import com.ecom.productservice.dto.OrderDto;
import com.ecom.productservice.dto.OrderItemDto;
import com.ecom.productservice.dto.OrderItemListResponse;
import com.ecom.productservice.dto.OrderItemResponse;
import com.ecom.productservice.dto.OrderListResponse;
import com.ecom.productservice.dto.OrderResponse;
import com.ecom.productservice.entity.Order;
import com.ecom.productservice.service.OrderItemService;
import com.ecom.productservice.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderDto orderDto){
		OrderResponse orderResponse = orderService.createOrder(orderDto);
		return ResponseEntity.ok(orderResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
		OrderResponse orderResponse = orderService.getOrderById(id);
		return ResponseEntity.ok(orderResponse);
	}
	
	@GetMapping
	public ResponseEntity<OrderListResponse> getAllOrder(){
		OrderListResponse orderListResponse = orderService.getAllOrder();
		return ResponseEntity.ok(orderListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,@Valid @RequestBody OrderDto orderDto){
		OrderResponse orderResponse = orderService.updateOrder(id, orderDto);
		return ResponseEntity.ok(orderResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long id) {
		OrderResponse orderResponse = orderService.deleteOrder(id);
		return ResponseEntity.ok(orderResponse);
	}
	
	@GetMapping("/page")
    public List<Order> getOrderWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return orderService.getOrderByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  
	

}
