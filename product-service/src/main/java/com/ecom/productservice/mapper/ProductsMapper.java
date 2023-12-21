package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.productservice.dto.ProductsDto;
import com.ecom.productservice.entity.Products;


@Component
public class ProductsMapper extends ModelMapper {
	
	public Products map(ProductsDto productsDto) {
		return this.map(productsDto, Products.class) ;
		}
	
	public ProductsDto map(Products products) {
		return this.map(products, ProductsDto.class);
	}
	
//	public Products map(ProductsDto productsDto, Products products) {
//		return this.map(productsDto, Products.class);
//	}
	
	public List<ProductsDto>  map (List<Products> productsList) {
		return productsList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
}


