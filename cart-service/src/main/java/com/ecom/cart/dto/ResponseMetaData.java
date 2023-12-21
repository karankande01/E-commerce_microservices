package com.ecom.cart.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ResponseMetaData {
	
		private String code;
		private String message;

	}

