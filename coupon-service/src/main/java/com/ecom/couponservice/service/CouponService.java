package com.ecom.couponservice.service;

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

import com.ecom.couponservice.constants.EcomConstant;
import com.ecom.couponservice.dto.CouponDto;
import com.ecom.couponservice.dto.CouponListResponse;
import com.ecom.couponservice.dto.CouponResponse;
import com.ecom.couponservice.entity.Coupon;
import com.ecom.couponservice.mapper.CouponMapper;
import com.ecom.couponservice.mapper.ResponseMetaDataMapper;
import com.ecom.couponservice.repository.CouponRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CouponService {
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public CouponResponse createCoupon (CouponDto couponDto) {
		
		Coupon coupon = couponMapper.map(couponDto);
		coupon = couponRepository.save(coupon);

		CouponResponse couponResponse = CouponResponse.builder()
				.data(couponMapper.map(coupon))
				.meta(metaDataMapper.map(EcomConstant.COUPON_CREATE_SUCCESS_CODE, EcomConstant.COUPON_CREATE_SUCCESS_MESSAGE))
				.build();
		
		return couponResponse;
	}
	
	public CouponResponse getCouponById(Long id) {
		Optional<Coupon> coupon = couponRepository.findById(id);
		if(coupon.isPresent()) {
			
		}
		Coupon coupon2 = coupon.get();
		CouponResponse couponResponse = CouponResponse.builder()
				.data(couponMapper.map(coupon2))
				.meta(metaDataMapper.map(EcomConstant.COUPON_FETCH_CODE, EcomConstant.COUPON_FETCH_MESSAGE))
				.build();
		
		return couponResponse;
	} 
	
	public CouponListResponse getAllCoupon() {
		List<Coupon> coupon = couponRepository.findAll();
		
		CouponListResponse couponListResponse = CouponListResponse.builder()
				.data(couponMapper.map(coupon))
				.meta(metaDataMapper.map(EcomConstant.COUPON_FETCH_CODE, EcomConstant.COUPON_FETCH_MESSAGE))
				.build();
		
		return couponListResponse;
	}
	
	public CouponResponse updateCoupon(Long id,CouponDto couponDto) {
		
		Optional<Coupon> coupon = couponRepository.findById(id);
		
		if(coupon.isPresent()) {
			
		}
		Coupon coupon2 = coupon.get();
		coupon2 = couponMapper.map(couponDto);
		coupon2 = couponRepository.save(coupon2);
		
		CouponResponse couponResponse = CouponResponse.builder()
				.data(couponMapper.map(coupon2))
				.meta(metaDataMapper.map(EcomConstant.COUPON_UPDATE_CODE, EcomConstant.COUPON_UPDATE_MESSAGE))
				.build();
		
		return couponResponse;
	}
	
	public CouponResponse deleteCoupon(Long id) {
		Optional<Coupon> coupon = couponRepository.findById(id);
		if(!coupon.isPresent()) {
			
		}
		couponRepository.delete(coupon.get());
		
		return CouponResponse.builder()
				.meta(metaDataMapper.map(EcomConstant.COUPON_DELETE_CODE, EcomConstant.COUPON_DELETE_MESSAGE))
				.build();
	}

	
	public List<Coupon> getCouponByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<Coupon> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<Coupon> pagingUser = couponRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}

}
