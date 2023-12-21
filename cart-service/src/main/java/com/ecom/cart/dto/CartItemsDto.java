package com.ecom.cart.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartItemsDto {
   
	private Long cartItemId;

	//handle this using another service
    //private Users users;

    private List<Long> products_ids = new ArrayList<Long>();
    
    private int quantity;
    
    private BigDecimal totalPrice;
    
    private String productCategory;

}

