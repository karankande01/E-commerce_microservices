package com.ecom.cart.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.cart.dto.CartItemsDto;
import com.ecom.cart.entity.CartItems;

@Component
public class CartItemsMapper extends ModelMapper{
	
		
		public CartItems map(CartItemsDto cartItemsDto) {
			return this.map(cartItemsDto, CartItems.class);
		}
		
		public CartItemsDto map(CartItems cartItems) {
			return this.map(cartItems,CartItemsDto.class);
		}
		
		public List<CartItemsDto> map(List<CartItems> cartItemsList){
			return cartItemsList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
		}
	    
		public CartItems map(CartItemsDto cartItemsDto, CartItems cartItems) {
			return this.map(cartItemsDto, CartItems.class);
		}
}
