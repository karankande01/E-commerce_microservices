package com.ecom.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
	
	private ResponseMetaData meta;
	private OrderItemDto data;
	
}
