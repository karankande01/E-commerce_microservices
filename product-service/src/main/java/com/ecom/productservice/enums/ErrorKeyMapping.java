package com.ecom.productservice.enums;

import org.springframework.http.HttpStatus;
import com.ecom.productservice.constant.ProductsConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorKeyMapping {
	
	PRODUCT_SERVICE_DATA_CREATION_FAILED(HttpStatus.ACCEPTED, ProductsConstants.PRODUCT_SERVICE_DATA_CREATION_FAILED), 
	PRODUCTS_SERVICE_DATA_NOT_FOUND(HttpStatus.ACCEPTED, ProductsConstants.PRODUCT_SERVICE_DATA_CREATION_FAILED);
	
	private HttpStatus httpStatus;
	private String errorMessageKey;

}
