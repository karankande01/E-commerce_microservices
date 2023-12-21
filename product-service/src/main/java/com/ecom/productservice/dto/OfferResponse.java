package com.ecom.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferResponse {
	
	private ResponseMetaData meta;
	private OfferDto data;	
	
	}

