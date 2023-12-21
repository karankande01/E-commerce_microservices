package com.ecom.shipping.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingListResponse {
	
	private ResponseMetaData meta;
	private List<ShippingDto> data;

}
