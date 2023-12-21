package com.ecom.couponservice.dto;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponDto {

   
    private Long couponId;

    
    @Column(name = "coupon_code", unique = true)
    private String couponCode;

    
    @Column(name = "coupon_name")
    private String couponName;

    
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "minimum_purchase_amount")
    private BigDecimal minimumPurchaseAmount;

    
    @Column(name = "coupon_type")
    private String couponType;

   
    @Column(name = "is_active")
    private boolean isActive;

    
}

