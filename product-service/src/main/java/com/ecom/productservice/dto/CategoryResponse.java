package com.ecom.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
	
	private ResponseMetaData meta;
	private CategoryDto data;	

}
