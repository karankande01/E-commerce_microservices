package com.ecom.productservice.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.ecom.productservice.entity.Brand;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.RatingAndReview;
import com.ecom.productservice.entity.WishList;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductsDto {

	    private long productId;

	    private String productName;
	    
	    private String imagePath;
	    
	    private String modelName;

	    private String actualPrice;

	    private String productColor;

	    private String productBrand;

	    private String discountedPrice;

	    private String memorySize;
	    
	    private SpecificationsDto specifications;
	    
	    private List<RatingAndReviewDto> ratingAndReview = new ArrayList<RatingAndReviewDto>();
	    
	    private BrandDto brand;  

	    private Category category;
	    
	 //   @JsonFormat(pattern = "dd-MM-yyyy")
	    private LocalDateTime launchDate;
	    
	    private LocalDateTime createdAt;
	    
	    private LocalDateTime updatedAt; 
	   
}
