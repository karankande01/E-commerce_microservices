package com.ecom.productservice.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	@NotNull(message = "User ID is required")
    private long userId;

    @NotEmpty(message = "Products list cannot be empty")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();  
}
