package com.ecom.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMetaData {
	
		private String code;
		private String message;

	}

