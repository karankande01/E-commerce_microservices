package com.ecom.couponservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponResponse {
	
	private ResponseMetaData meta;
	private CouponDto data;
	

}
