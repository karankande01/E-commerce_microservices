package com.ecom.productservice.dto;

import java.util.List;

import com.ecom.productservice.entity.Products;

import lombok.Data;

@Data
public class BrandDto {
  
	    private Long brandId;
	    
	    private String brandName;
	    
	    private String description;
	    
	    private List<Products> products;

}
