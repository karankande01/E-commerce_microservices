package com.ecom.couponservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.couponservice.dto.CouponDto;
import com.ecom.couponservice.entity.Coupon;

@Component
public class CouponMapper extends ModelMapper {
	
	public Coupon map(CouponDto couponDto) {
		return this.map(couponDto, Coupon.class);
	}
	
	public CouponDto map(Coupon coupon) {
		return this.map(coupon,CouponDto.class);
	}
	
	public List<CouponDto> map(List<Coupon> couponList){
		return couponList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
    
	public Coupon map(CouponDto couponDto, Coupon coupon) {
		return this.map(couponDto, Coupon.class);
	}

}
