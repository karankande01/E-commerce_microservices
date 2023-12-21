package com.ecom.shipping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingResponse {
	
	private ResponseMetaData meta;
	private ShippingDto data;
	
	

}
