package com.ecom.productservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import com.ecom.productservice.entity.Products;

@Data
public class CategoryDto {

   
	    private Long categoryId;

	    private String categoryName;

	    private List<Products> products;
}

