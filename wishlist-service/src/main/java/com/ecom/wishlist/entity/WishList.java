package com.ecom.wishlist.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="wishlist")
public class WishList {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
        private long wishListId;
	
	    //needs to handle with other service
//	    @NotBlank(message = "User ID is required")
//	    @OneToOne(cascade = CascadeType.ALL)
//	    @JoinColumn(name = "user_id")
//	    private Users users;

	    @NotEmpty(message = "Products list cannot be empty")
	    @Transient
	    private List<@NotNull(message = "Product cannot be null") Products> products = new ArrayList<>(); 
}

