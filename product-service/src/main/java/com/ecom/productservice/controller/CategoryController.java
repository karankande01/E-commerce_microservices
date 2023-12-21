package com.ecom.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.productservice.dto.CategoryDto;
import com.ecom.productservice.dto.CategoryListResponse;
import com.ecom.productservice.dto.CategoryResponse;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryResponse categoryResponse = categoryService.createCategory(categoryDto);
		return ResponseEntity.ok(categoryResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
		CategoryResponse categoryResponse = categoryService.getCategoryById(id);
		return ResponseEntity.ok(categoryResponse);
	}
	
	@GetMapping
	public ResponseEntity<CategoryListResponse> getAllCategory(){
		CategoryListResponse categoryListResponse = categoryService.getAllCategory();
		return ResponseEntity.ok(categoryListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto){
		CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryDto);
		return ResponseEntity.ok(categoryResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
		CategoryResponse categoryResponse = categoryService.deleteCategory(id);
		return ResponseEntity.ok(categoryResponse);
	}
	
	@GetMapping("/page")
    public List<Category> getCategoryWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return categoryService.getCategoryByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  

}
