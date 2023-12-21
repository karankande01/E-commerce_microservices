package com.ecom.shipping.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.shipping.dto.ShippingDto;
import com.ecom.shipping.entity.Shipping;

@Component
public class ShippingMapper extends ModelMapper {
	
	public Shipping map(ShippingDto shippingDto) {
		return this.map(shippingDto, Shipping.class);
	}
	
	public ShippingDto map(Shipping shipping) {
		return this.map(shipping, ShippingDto.class);
	}
	
	public List<ShippingDto> map(List<Shipping> shippingList){
		return shippingList.stream().map(this::map).collect(Collectors.toList());
	}
    
	public Shipping map(ShippingDto shippingDto, Shipping shipping) {
		return this.map(shippingDto, Shipping.class);
	}

}
