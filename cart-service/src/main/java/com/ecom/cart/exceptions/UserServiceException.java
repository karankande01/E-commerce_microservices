package com.ecom.cart.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.cart.enums.ErrorKeyMapping;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private ErrorKeyMapping errorKeyMapping;
	
	public UserServiceException(ErrorKeyMapping errorKeyMapping) {
		super(errorKeyMapping.getErrorMessageKey());
		this.errorKeyMapping = errorKeyMapping;
	}
}