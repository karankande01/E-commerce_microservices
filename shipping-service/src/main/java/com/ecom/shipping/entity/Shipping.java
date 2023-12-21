package com.ecom.shipping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "shippings")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @NotBlank(message = "Shipping method name is required")
    @Size(max = 255, message = "Shipping method name cannot exceed 255 characters")
    @Column(name = "method_name")
    private String methodName;

    @NotBlank(message = "Shipping provider name is required")
    @Size(max = 255, message = "Shipping provider name cannot exceed 255 characters")
    @Column(name = "provider_name")
    private String providerName;

    @NotNull(message = "Shipping cost is required")
    @DecimalMin(value = "0.01", message = "Shipping cost must be greater than 0")
    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @NotBlank(message = "Estimated delivery time is required")
    @Size(max = 50, message = "Estimated delivery time cannot exceed 50 characters")
    @Column(name = "estimated_delivery_time")
    private String estimatedDeliveryTime;

    @NotBlank(message = "Shipping region is required")
    @Size(max = 255, message = "Shipping region cannot exceed 255 characters")
    @Column(name = "shipping_region")
    private String shippingRegion;

    
}
