package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.productservice.dto.OrderDto;
import com.ecom.productservice.entity.Order;

@Component
public class OrderMapper extends ModelMapper {
	
	public Order map (OrderDto orderDto ) {
		return this.map(orderDto,Order.class);
	}
	
	public OrderDto map (Order order) {
		return this.map(order ,OrderDto.class);
	}
	
	public List<OrderDto> map(List<Order> orderList){
		return orderList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
	
	public Order map(OrderDto orderDto, Order order) {
		return this.map(orderDto, Order.class);
	}
    

}
