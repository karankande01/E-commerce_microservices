package com.ecom.productservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsResponse {
	
	private ResponseMetaData meta;
	private ProductsDto data;
	
}
