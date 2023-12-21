package com.ecom.productservice.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String msg;
	 
	public ProductNotFoundException(String msg){
		super(msg);
	}
}
