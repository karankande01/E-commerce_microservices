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
import com.ecom.productservice.dto.OrderItemDto;
import com.ecom.productservice.dto.OrderItemListResponse;
import com.ecom.productservice.dto.OrderItemResponse;
import com.ecom.productservice.entity.OrderItem;
import com.ecom.productservice.service.OrderItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderitem")
@Validated
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping
	public ResponseEntity<OrderItemResponse> createOrderItem(@Valid @RequestBody OrderItemDto orderItemDto){
		OrderItemResponse orderItemResponse = orderItemService.createOrderItem(orderItemDto);
		return ResponseEntity.ok(orderItemResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable Long id) {
		OrderItemResponse orderItemResponse = orderItemService.getOrderItemById(id);
		return ResponseEntity.ok(orderItemResponse);
	}
	
	@GetMapping
	public ResponseEntity<OrderItemListResponse> getAllOrderItem(){
		OrderItemListResponse orderItemListResponse = orderItemService.getAllOrderItem();
		return ResponseEntity.ok(orderItemListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderItemResponse> updateOrderItem(@PathVariable Long id,@Valid @RequestBody OrderItemDto orderItemDto){
		OrderItemResponse orderItemResponse = orderItemService.updateOrderItem(id, orderItemDto);
		return ResponseEntity.ok(orderItemResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OrderItemResponse> deleteOrderItem(@PathVariable Long id) {
		OrderItemResponse orderItemResponse = orderItemService.deleteOrderItem(id);
		return ResponseEntity.ok(orderItemResponse);
	}
	
	@GetMapping("/page")
    public List<OrderItem> getUserWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return orderItemService.getOrderItemByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  

}
