package com.ecom.wishlist.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//@Embeddable
@Entity
public class Products {
	
	@Id
    private long productId;
    @NotBlank(message = "Product name is required")
    @Size(max = 15, message = "Product name cannot exceed 15 characters")
    @Column(name = "product_name", length = 15)
    private String productName;

    @NotBlank(message = "Model name is required")
    @Size(max = 15, message = "Model name cannot exceed 15 characters")
    @Column(name = "model_name", length = 15)
    private String modelName;

    @NotBlank(message = "Actual price is required")
    @Pattern(regexp = "^\\d{0,15}(\\.\\d{1,2})?$", message = "Invalid actual price format")
    @Column(name = "actual_price", length = 15)
    private String actualPrice;

    @NotBlank(message = "Discounted price is required")
    @Pattern(regexp = "^\\d{0,15}(\\.\\d{1,2})?$", message = "Invalid discounted price format")
    @Column(name = "discounted_price", length = 15)
    private String discountedPrice;

    @NotBlank(message = "Product color is required")
    @Size(max = 15, message = "Product color cannot exceed 15 characters")
    @Column(name = "color", length = 15)
    private String productColor;

    @NotBlank(message = "Product brand is required")
    @Size(max = 15, message = "Product brand cannot exceed 15 characters")
    @Column(name = "product_brand", length = 15)
    private String productBrand;

    @Valid
    @OneToOne
    private Specifications specifications;
    
    @OneToMany
    private List<@Valid RatingAndReview> ratingAndReview = new ArrayList<>();
    

    
}
