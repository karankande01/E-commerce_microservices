package com.ecom.shipping.enums;

import org.springframework.http.HttpStatus;

import com.ecom.shipping.constants.EcomConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorKeyMapping {

	ECOM_USERSERVICE_DATA_NOT_FOUND(HttpStatus.ACCEPTED, EcomConstant.ECOM_USERSERVICE_DATA_NOT_FOUND),
	ECOM_USERSERVICE_USER_ALREADY_EXIST(HttpStatus.ACCEPTED,EcomConstant.ECOM_USERSERVICE_USER_ALREADY_EXIST);
	
	
	
	    private HttpStatus httpStatus;
	    private String errorMessageKey;
		
	
	
}
