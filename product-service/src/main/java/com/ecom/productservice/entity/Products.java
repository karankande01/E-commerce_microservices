package com.ecom.productservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

	@NotBlank(message = "Product name is required")
    @Size(max = 15, message = "Product name cannot exceed 15 characters")
    @Column(name = "product_name", length = 15)
    private String productName;
    
    @Column(name = "image_path")
    private String imagePath;
    
    private String memorySize;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specification_id")
    private Specifications specifications;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RatingAndReview> ratingAndReview = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;
    
//    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "launch_date")
    private LocalDateTime launchDate;
    
    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime createdAt;
    
    @CreationTimestamp
    @Column(name = "careated_at")
    private LocalDateTime updatedAt; 

}
