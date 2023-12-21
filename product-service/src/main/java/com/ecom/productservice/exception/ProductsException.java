package com.ecom.productservice.exception;

import com.ecom.productservice.enums.ErrorKeyMapping;
import lombok.Getter;

@Getter
public class ProductsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorKeyMapping errorKeyMapping;
	
	public ProductsException(ErrorKeyMapping errorKeyMapping) {
		super(errorKeyMapping.getErrorMessageKey());
		this.errorKeyMapping = errorKeyMapping;
	}
	
	public ProductsException(String message, ErrorKeyMapping errorKeyMapping) {
		super(message);
		this.errorKeyMapping = errorKeyMapping;
	}
	
	public ProductsException(String message, Throwable cause, ErrorKeyMapping errorKeyMapping) {
		super(message, cause);
		this.errorKeyMapping = errorKeyMapping;
	}

	public ProductsException(Throwable cause, ErrorKeyMapping errorKeyMapping) {
		super(cause);
		this.errorKeyMapping = errorKeyMapping;
	}

	public ProductsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorKeyMapping errorKeyMapping) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorKeyMapping = errorKeyMapping;
	}
}

