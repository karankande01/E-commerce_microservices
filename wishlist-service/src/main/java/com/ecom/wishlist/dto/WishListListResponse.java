package com.ecom.wishlist.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListListResponse {
		
		private ResponseMetaData meta;
		private List<WishListDto> data;

	}



