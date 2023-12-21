package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ecom.productservice.dto.OfferDto;
import com.ecom.productservice.entity.Offer;


@Component
public class OfferMapper extends ModelMapper{
	
	public Offer map (OfferDto offerDto ) {
		return this.map(offerDto,Offer.class);
	}
	
	public OfferDto map (Offer offer) {
		return this.map(offer ,OfferDto.class);
	}
	
	public List<OfferDto> map(List<Offer> offerList){
		return offerList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
	
	public Offer map(OfferDto offerDto, Offer offer) {
		return this.map(offerDto, Offer.class);
	}

}
