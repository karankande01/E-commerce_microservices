package com.ecom.productservice.mapper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.ecom.productservice.dto.ResponseMetaData;

@Component
public class ResponseMetaDataMapper {
	
	@Autowired
	private MessageSource messageSource;
	
	public ResponseMetaData map(String code, String message) {
		return ResponseMetaData.builder()
				.code(code)
				.message(messageSource.getMessage(message, new Object[0],Locale.getDefault()))
				.build();
		
	}
}