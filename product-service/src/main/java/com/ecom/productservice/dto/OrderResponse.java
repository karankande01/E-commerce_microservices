package com.ecom.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	
	private ResponseMetaData meta;
	private OrderDto data;	
	}


