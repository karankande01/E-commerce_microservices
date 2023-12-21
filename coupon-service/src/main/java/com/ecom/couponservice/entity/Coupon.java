package com.ecom.couponservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @NotBlank(message = "Coupon code is required")
    @Size(max = 50, message = "Coupon code cannot exceed 50 characters")
    @Column(name = "coupon_code", unique = true)
    private String couponCode;

    @NotBlank(message = "Coupon name is required")
    @Size(max = 255, message = "Coupon name cannot exceed 255 characters")
    @Column(name = "coupon_name")
    private String couponName;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @NotNull(message = "End date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @NotNull(message = "Discount amount is required")
    @DecimalMin(value = "0.01", message = "Discount amount must be greater than 0")
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "minimum_purchase_amount")
    private BigDecimal minimumPurchaseAmount;

    @NotBlank(message = "Coupon type is required")
    @Size(max = 50, message = "Coupon type cannot exceed 50 characters")
    @Column(name = "coupon_type")
    private String couponType;

    @NotNull(message = "Active status is required")
    @Column(name = "is_active")
    private boolean isActive;

   
}
