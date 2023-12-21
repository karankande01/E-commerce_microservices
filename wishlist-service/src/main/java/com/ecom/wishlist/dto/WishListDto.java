package com.ecom.wishlist.dto;


import java.util.ArrayList;
import java.util.List;

import com.ecom.wishlist.entity.Products;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class WishListDto {


    private long wishListId;
	
	private long usersId;
	
	private List<Products> products = new ArrayList<>();  
}

