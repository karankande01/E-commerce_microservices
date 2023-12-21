package com.ecom.wishlist.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.wishlist.dto.WishListDto;
import com.ecom.wishlist.entity.WishList;

@Component
public class WishListMapper extends ModelMapper {
	
		
		public WishList map(WishListDto wishListDto) {
			return this.map(wishListDto, WishList.class);
		}
		
		public WishListDto map(WishList wishList) {
			return this.map(wishList,WishListDto.class);
		}
		
		public List<WishListDto> map(List<WishList> wishListList){
			return wishListList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
		}
	    
		public WishList map(WishListDto wishListDto, WishList wishList) {
			return this.map(wishListDto, WishList.class);
		}

	}
