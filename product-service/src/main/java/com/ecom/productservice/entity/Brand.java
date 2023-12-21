package com.ecom.productservice.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Brand {
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;
    private String brandName;
    private String description;
    
    @OneToMany(mappedBy = "brand")
    private List<Products> products;

}