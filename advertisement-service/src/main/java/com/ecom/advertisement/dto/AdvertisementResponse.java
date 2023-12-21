package com.ecom.advertisement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdvertisementResponse {
	
	private ResponseMetaData meta;
	private AdvertisementDto data;

}
