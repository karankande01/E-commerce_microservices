package com.ecom.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemsResponse {
	
	private ResponseMetaData meta;
	private CartItemsDto data;

}
