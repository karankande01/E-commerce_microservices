package com.ecom.productservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import com.ecom.productservice.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @Valid
    @NotEmpty(message = "Order items cannot be empty")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<@NotNull(message = "Order item cannot be null") OrderItem> orderItems;

    @NotNull(message = "Order date is required")
    @Column(name = "order_date")
    @CreationTimestamp
    private Date orderDate;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.01", message = "Total price must be greater than 0")
    private BigDecimal totalPrice;

    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

}

