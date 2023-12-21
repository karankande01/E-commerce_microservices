package com.ecom.advertisement.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdvertisementListResponse {
	
	private ResponseMetaData meta;
	private List<AdvertisementDto> data;


}
