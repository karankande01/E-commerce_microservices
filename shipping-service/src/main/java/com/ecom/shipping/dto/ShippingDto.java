package com.ecom.shipping.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ShippingDto {
	
	private Long shippingId;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @Column(name = "estimated_delivery_time")
    private String estimatedDeliveryTime;

    @Column(name = "shipping_region")
    private String shippingRegion;
}
