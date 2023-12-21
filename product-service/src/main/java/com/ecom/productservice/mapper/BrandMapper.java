package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.productservice.dto.BrandDto;
import com.ecom.productservice.entity.Brand;

@Component
public class BrandMapper extends ModelMapper {
	
	public Brand map (BrandDto brandDto ) {
		return this.map(brandDto,Brand.class);
	}
	
	public BrandDto map (Brand brand) {
		return this.map(brand ,BrandDto.class);
	}
	
	public List<BrandDto> map(List<Brand> brandList){
		return brandList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
	
	public Brand map(BrandDto brandDto, Brand brand) {
		return this.map(brandDto, Brand.class);
	}


}
