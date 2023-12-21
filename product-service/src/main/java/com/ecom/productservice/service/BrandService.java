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
import com.ecom.productservice.dto.BrandDto;
import com.ecom.productservice.dto.BrandListResponse;
import com.ecom.productservice.dto.BrandResponse;
import com.ecom.productservice.entity.Brand;
import com.ecom.productservice.mapper.BrandMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.repository.BrandRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
public BrandResponse createBrand (BrandDto brandDto) {
		
		Brand brand = brandMapper.map(brandDto);
	    brand =	brandRepository.save(brand);
	    
	    BrandResponse brandResponse = BrandResponse.builder()
	    		.data(brandMapper.map(brand))
	    		.meta(metaDataMapper.map(ProductsConstants.BRAND_SERVICE_SUCCESS_CODE, ProductsConstants.BRAND_SERVICE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return brandResponse;
	}

public BrandResponse getBrandById (Long id) {
	
	Optional<Brand> brand = brandRepository.findById(id);
	
	if(!brand.isPresent()) {
		
	}
	Brand brand2 = brand.get();
	BrandResponse brandResponse = BrandResponse.builder()
			.data(brandMapper.map(brand2))
			.meta(metaDataMapper.map(ProductsConstants.BRAND_SERVICE_FETCH_CODE, ProductsConstants.BRAND_SERVICE_FETCH_MESSAGE))
			.build();
	
	return brandResponse;
}

public BrandListResponse getAllBrand() {
	List<Brand> brands = brandRepository.findAll();
	
	BrandListResponse brandListResponse = BrandListResponse.builder()
			.data(brandMapper.map(brands))
			.meta(metaDataMapper.map(ProductsConstants.BRAND_SERVICE_FETCH_CODE, ProductsConstants.BRAND_SERVICE_FETCH_MESSAGE))
			.build();
	
	return brandListResponse;
}

public BrandResponse updateBrand (Long id, BrandDto brandDto) {
	Optional<Brand> brand = brandRepository.findById(id);
	
	if(!brand.isPresent()) {
		
	}
	
	Brand brand2 = brand.get();
	brand2 = brandMapper.map(brandDto);
	brand2 = brandRepository.save(brand2);
	
	BrandResponse brandResponse =BrandResponse.builder()
			.data(brandMapper.map(brand2))
			.meta(metaDataMapper.map(ProductsConstants.BRAND_SERVICE_UPDATE_CODE, ProductsConstants.BRAND_SERVICE_UPDATE_MESSAGE))
			.build();
	
	return brandResponse;
}

public BrandResponse deleteBrand (Long id) {
	Optional<Brand> brand = brandRepository.findById(id);
	if(!brand.isPresent()) {
		
	}
	
	brandRepository.delete(brand.get());
	 
	 return BrandResponse.builder()
			 .meta(metaDataMapper.map(ProductsConstants.BRAND_SERVICE__DELETE_SUCCESS_CODE, ProductsConstants.BRAND_SERVICE_DELETE_SUCCESS_MESSAGE))
			 .build();
}

public List<Brand> getBrandByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
    
    // Split the searchKeywords into individual terms
    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
    
    // Create a Specification for the search criteria
    Specification<Brand> specification = (root, query, criteriaBuilder) -> {
    	
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

    Page<Brand> pagingUser = brandRepository.findAll(specification,pageRequest);
    return pagingUser.getContent();
}



}
