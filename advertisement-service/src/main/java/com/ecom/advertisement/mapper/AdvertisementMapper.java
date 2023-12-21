package com.ecom.advertisement.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.advertisement.dto.AdvertisementDto;
import com.ecom.advertisement.entity.Advertisement;

@Component
public class AdvertisementMapper extends ModelMapper{
	
	public Advertisement map(AdvertisementDto advertisementDto) {
		return this.map(advertisementDto, Advertisement.class);
	}
	
	public AdvertisementDto map(Advertisement advertisement) {
		return this.map(advertisement,AdvertisementDto.class);
	}
	
	public List<AdvertisementDto> map(List<Advertisement> advertisementList){
		return advertisementList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
    
	public Advertisement map(AdvertisementDto advertisementDto, Advertisement advertisement) {
		return this.map(advertisementDto, Advertisement.class);
	}

}
