package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ecom.productservice.dto.OrderItemDto;
import com.ecom.productservice.entity.OrderItem;

@Component
public class OrderItemMapper extends ModelMapper {
	
	public OrderItem map (OrderItemDto orderItemDto ) {
		return this.map(orderItemDto,OrderItem.class);
	}
	
	public OrderItemDto map (OrderItem orderItem) {
		return this.map(orderItem ,OrderItemDto.class);
	}
	
	public List<OrderItemDto> map(List<OrderItem> orderItemList){
		return orderItemList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
	
	public OrderItem map(OrderItemDto orderItemDto, OrderItem orderItem) {
		return this.map(orderItemDto, OrderItem.class);
	}
    
	

}
