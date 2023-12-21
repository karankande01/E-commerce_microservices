package com.ecom.productservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.productservice.dto.CategoryDto;
import com.ecom.productservice.entity.Category;

@Component
public class CategoryMapper extends ModelMapper {
	
	public Category map (CategoryDto categoryDto ) {
		return this.map(categoryDto,Category.class);
	}
	
	public CategoryDto map (Category category) {
		return this.map(category ,CategoryDto.class);
	}
	
	public List<CategoryDto> map(List<Category> categoryList){
		return categoryList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
	
	public Category map(CategoryDto categoryDto, Category category) {
		return this.map(categoryDto, Category.class);
	}

}
