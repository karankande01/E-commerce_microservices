package com.ecom.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @NotBlank(message = "Offer name is required")
    @Size(max = 255, message = "Offer name cannot exceed 255 characters")
    @Column(name = "offer_name")
    private String offerName;

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

    @NotNull(message = "Discount percentage is required")
    @DecimalMin(value = "0.01", message = "Discount percentage must be greater than 0")
    @DecimalMax(value = "100.00", message = "Discount percentage must be less than or equal to 100")
    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage;

    @Column(name = "minimum_purchase_amount")
    private BigDecimal minimumPurchaseAmount;

    @NotBlank(message = "Coupon code is required")
    @Size(max = 50, message = "Coupon code cannot exceed 50 characters")
    @Column(name = "coupon_code")
    private String couponCode;

    @NotNull(message = "Active status is required")
    @Column(name = "is_active")
    private boolean isActive;

    // Other relevant fields such as usage limits, product categories, etc.

    // Getters and setters...
}
