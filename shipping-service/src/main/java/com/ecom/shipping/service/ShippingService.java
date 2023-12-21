package com.ecom.shipping.service;

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

import com.ecom.shipping.constants.EcomConstant;
import com.ecom.shipping.dto.ShippingDto;
import com.ecom.shipping.dto.ShippingListResponse;
import com.ecom.shipping.dto.ShippingResponse;
import com.ecom.shipping.entity.Shipping;
import com.ecom.shipping.mapper.ResponseMetaDataMapper;
import com.ecom.shipping.mapper.ShippingMapper;
import com.ecom.shipping.repository.ShippingRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShippingService {
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private ShippingMapper shippingMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
public ShippingResponse createShipping (ShippingDto shippingDto) {
		
		Shipping shipping = shippingMapper.map(shippingDto);
		shipping = shippingRepository.save(shipping);

		ShippingResponse shippingResponse = ShippingResponse.builder()
				.data(shippingMapper.map(shipping))
				.meta(metaDataMapper.map(EcomConstant.SHIPPING_CREATE_SUCCESS_CODE, EcomConstant.SHIPPING_CREATE_SUCCESS_MESSAGE))
				.build();
		
		return shippingResponse;
	}
	
	public ShippingResponse getShippingById(Long id) {
		Optional<Shipping> shipping = shippingRepository.findById(id);
		if(shipping.isPresent()) {
			
		}
		Shipping shipping2 = shipping.get();
		ShippingResponse shippingResponse = ShippingResponse.builder()
				.data(shippingMapper.map(shipping2))
				.meta(metaDataMapper.map(EcomConstant.SHIPPING_FETCH_CODE, EcomConstant.SHIPPING_FETCH_MESSAGE))
				.build();
		
		return shippingResponse;
	} 
	
	public ShippingListResponse getAllShipping() {
		List<Shipping> shipping = shippingRepository.findAll();
		
		ShippingListResponse shippingListResponse = ShippingListResponse.builder()
				.data(shippingMapper.map(shipping))
				.meta(metaDataMapper.map(EcomConstant.SHIPPING_FETCH_CODE, EcomConstant.SHIPPING_FETCH_MESSAGE))
				.build();
		
		return shippingListResponse;
	}
	
	public ShippingResponse updateShipping(Long id,ShippingDto shippingDto) {
		
		Optional<Shipping> shipping = shippingRepository.findById(id);
		
		if(shipping.isPresent()) {
			
		}
		Shipping shipping2 = shipping.get();
		shipping2 = shippingMapper.map(shippingDto);
		shipping2 = shippingRepository.save(shipping2);
		
		ShippingResponse shippingResponse = ShippingResponse.builder()
				.data(shippingMapper.map(shipping2))
				.meta(metaDataMapper.map(EcomConstant.SHIPPING_UPDATE_CODE, EcomConstant.SHIPPING_UPDATE_MESSAGE))
				.build();
		
		return shippingResponse;
	}
	
	public ShippingResponse deleteShipping(Long id) {
		Optional<Shipping> shipping = shippingRepository.findById(id);
		if(!shipping.isPresent()) {
			
		}
		shippingRepository.delete(shipping.get());
		
		return ShippingResponse.builder()
				.meta(metaDataMapper.map(EcomConstant.SHIPPING_DELETE_CODE, EcomConstant.SHIPPING_DELETE_MESSAGE))
				.build();
	}
	
	
	public List<Shipping> getShippingByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<Shipping> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<Shipping> pagingUser = shippingRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	
	

}
