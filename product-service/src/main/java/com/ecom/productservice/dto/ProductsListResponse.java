package com.ecom.productservice.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsListResponse {
 
	 private ResponseMetaData meta;
	 private List<ProductsDto> data;
}
