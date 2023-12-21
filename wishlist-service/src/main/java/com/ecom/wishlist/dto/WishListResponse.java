package com.ecom.wishlist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishListResponse {
		
		private ResponseMetaData meta;
		private WishListDto data;

}
