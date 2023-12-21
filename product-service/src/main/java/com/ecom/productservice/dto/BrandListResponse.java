package com.ecom.productservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandListResponse {
	
	private ResponseMetaData meta;
	 private List<BrandDto> data;

}
