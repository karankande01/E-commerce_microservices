package com.ecom.cart.dto;


import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemsListResponse {
	
	private ResponseMetaData meta;
	private List<CartItemsDto> data;

}
