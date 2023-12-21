package com.ecom.productservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecom.productservice.constant.ProductsConstants;
import com.ecom.productservice.dto.CategoryDto;
import com.ecom.productservice.dto.CategoryListResponse;
import com.ecom.productservice.dto.CategoryResponse;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.mapper.CategoryMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.repository.CategoryRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
public CategoryResponse createCategory (CategoryDto categoryDto) {
		
		Category category = categoryMapper.map(categoryDto);
	    category =	categoryRepository.save(category);
	    
	    CategoryResponse categoryResponse = CategoryResponse.builder()
	    		.data(categoryMapper.map(category))
	    		.meta(metaDataMapper.map(ProductsConstants.CATEGORY_SERVICE_SUCCESS_CODE, ProductsConstants.CATEGORY_SERVICE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return categoryResponse;
	}

public CategoryResponse getCategoryById (Long id) {
	
	Optional<Category> category = categoryRepository.findById(id);
	
	if(!category.isPresent()) {
		
	}
	Category category2 = category.get();
	CategoryResponse categoryResponse = CategoryResponse.builder()
			.data(categoryMapper.map(category2))
			.meta(metaDataMapper.map(ProductsConstants.CATEGORY_SERVICE_FETCH_CODE, ProductsConstants.CATEGORY_SERVICE_FETCH_MESSAGE))
			.build();
	
	return categoryResponse;
}

public CategoryListResponse getAllCategory() {
	List<Category> categorys = categoryRepository.findAll();
	
	CategoryListResponse categoryListResponse = CategoryListResponse.builder()
			.data(categoryMapper.map(categorys))
			.meta(metaDataMapper.map(ProductsConstants.CATEGORY_SERVICE_FETCH_CODE, ProductsConstants.CATEGORY_SERVICE_FETCH_MESSAGE))
			.build();
	
	return categoryListResponse;
}

public CategoryResponse updateCategory (Long id, CategoryDto categoryDto) {
	Optional<Category> category = categoryRepository.findById(id);
	
	if(!category.isPresent()) {
		
	}
	
	Category category2 = category.get();
	category2 = categoryMapper.map(categoryDto);
	category2 = categoryRepository.save(category2);
	
	CategoryResponse categoryResponse =CategoryResponse.builder()
			.data(categoryMapper.map(category2))
			.meta(metaDataMapper.map(ProductsConstants.CATEGORY_SERVICE_UPDATE_CODE, ProductsConstants.CATEGORY_SERVICE_UPDATE_MESSAGE))
			.build();
	
	return categoryResponse;
}

public CategoryResponse deleteCategory (Long id) {
	Optional<Category> category = categoryRepository.findById(id);
	if(!category.isPresent()) {
		
	}
	
	categoryRepository.delete(category.get());
	 
	 return CategoryResponse.builder()
			 .meta(metaDataMapper.map(ProductsConstants.CATEGORY_SERVICE__DELETE_SUCCESS_CODE, ProductsConstants.CATEGORY_SERVICE_DELETE_SUCCESS_MESSAGE))
			 .build();
}

public List<Category> getCategoryByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
    
    // Split the searchKeywords into individual terms
    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
    
    // Create a Specification for the search criteria
    Specification<Category> specification = (root, query, criteriaBuilder) -> {
    	
        List<Predicate> predicates = new ArrayList<>();

     // Add predicates for each field and each search term
        for (String term : searchTerms) {
            List<Predicate> fieldPredicates = new ArrayList<>();

            // Add predicates for each field
            for (String field : fields) {
                fieldPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + term.toLowerCase() + "%"));
            }

            // Combine field predicates with OR
            predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
        }

        // Combine the term predicates with AND
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

    Page<Category> pagingUser = categoryRepository.findAll(specification,pageRequest);
    return pagingUser.getContent();
}


}
