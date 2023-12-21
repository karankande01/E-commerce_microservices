package com.ecom.productservice.dto;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.CascadeType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;

import com.ecom.productservice.entity.Products;

import lombok.Data;

@Data
public class WishListDto {

	    private long wishListId;
		
		private long userId;

		private List<Products> products = new ArrayList<>();  
	}


