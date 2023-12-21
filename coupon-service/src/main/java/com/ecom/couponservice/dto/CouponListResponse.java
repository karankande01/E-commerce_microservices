package com.ecom.couponservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponListResponse {
	
	private ResponseMetaData meta;
	private List<CouponDto> data;

}
