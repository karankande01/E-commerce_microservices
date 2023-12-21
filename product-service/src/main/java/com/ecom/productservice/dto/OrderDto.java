package com.ecom.productservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import com.ecom.productservice.entity.OrderItem;
import com.ecom.productservice.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Data
public class OrderDto {

   
    private Long orderId;

    private String userId;

    private List<OrderItem> orderItems;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

}


