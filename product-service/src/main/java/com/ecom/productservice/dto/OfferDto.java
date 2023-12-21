package com.ecom.productservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class OfferDto {
	
	    private Long offerId;
	   
	    @Column(name = "offer_name")
	    private String offerName;
	   
	    private String description;
   
	    @Column(name = "start_date")
	    private Date startDate;
	  
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "end_date")
	    private Date endDate;

	    @Column(name = "discount_percentage")
	    private BigDecimal discountPercentage;

	    @Column(name = "minimum_purchase_amount")
	    private BigDecimal minimumPurchaseAmount;

	   
	    @Column(name = "coupon_code")
	    private String couponCode;

	    @Column(name = "is_active")
	    private boolean isActive;

}
