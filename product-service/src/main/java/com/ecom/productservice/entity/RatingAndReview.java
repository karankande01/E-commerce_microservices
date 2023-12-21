package com.ecom.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
@Table(name="rating_and_review")
public class RatingAndReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ratingAndReviewId;
	
	@NotNull(message = "User ID is required")
    private long userId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @Min(value = 1, message = "Average rating must be at least 1")
    @Max(value = 5, message = "Average rating must be at most 5")
    private int avgRating;

    @Size(max = 255, message = "Review comment cannot exceed 255 characters")
    private String reviewComment;

    @Size(max = 255, message = "Review image URL cannot exceed 255 characters")
    private String reviewImage;
    
//    @ManyToOne
//    @JoinColumn(name = "products_id")
//    private Products products;
    
}