package com.ecom.productservice.dto;

import lombok.Data;

@Data
public class RatingAndReviewDto{
	
private long ratingAndReviewId;
	
    private long userId;
    private int rating;
    private int avgRating;
    private String reviewComment;
    private String reviewImage;
    
}
