package com.ecom.productservice.dto;

import java.math.BigDecimal;
import java.util.List;
import com.ecom.productservice.entity.Products;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrderItemDto {
	
	  private Long orderItemId;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
    private String orders;

   private List<Products> products;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

	
}
