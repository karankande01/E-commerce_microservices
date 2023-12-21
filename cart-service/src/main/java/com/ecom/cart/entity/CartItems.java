package com.ecom.cart.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    //handle using another service
//    @NotNull(message = "User ID is required")
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private Users users;

    @NotNull(message = "Product ID is required")
    private List<Long> products_ids = new ArrayList<Long>();
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be positive")
    private BigDecimal totalPrice;
    
    @NotBlank(message = "Product category is required")
    private String productCategory;

//    @NotBlank(message = "Product name is required")
//    private String productName;
//
//    @NotNull(message = "Product price is required")
//    @Positive(message = "Product price must be positive")
//    private BigDecimal productPrice;

    

//    @NotNull(message = "Date added is required")
//    @PastOrPresent(message = "Date added must be in the past or present")
//    @CreationTimestamp
//    @Column(name = "date_added")
//    private LocalDateTime dateAdded;

    // other fields with validations...

//    @NotBlank(message = "Product image URL is required")
//    @URL(message = "Invalid product image URL")
//    @Column(name = "product_image")
//    private String productImage;
//
//    @Column(name = "availability")
//    private boolean availability;
//
//    @NotBlank(message = "Seller information is required")
//    private String sellerInformation;
//
//    @NotBlank(message = "Cart status is required")
//    private String cartStatus;

    // other fields with validations...
//
//    @Size(max = 255, message = "Discounts or promotions cannot exceed 255 characters")
//    @Column(name = "discounts_or_promotions")
//    private String discountsOrPromotions;


}

