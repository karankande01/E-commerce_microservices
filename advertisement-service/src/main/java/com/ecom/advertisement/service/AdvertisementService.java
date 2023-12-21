package com.ecom.advertisement.service;

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

import com.ecom.advertisement.constants.EcomConstant;
import com.ecom.advertisement.dto.AdvertisementDto;
import com.ecom.advertisement.dto.AdvertisementListResponse;
import com.ecom.advertisement.dto.AdvertisementResponse;
import com.ecom.advertisement.entity.Advertisement;
import com.ecom.advertisement.mapper.AdvertisementMapper;
import com.ecom.advertisement.mapper.ResponseMetaDataMapper;
import com.ecom.advertisement.repository.AdvertisementRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	@Autowired
	private AdvertisementMapper advertisementMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	public AdvertisementResponse createAdvertisement (AdvertisementDto advertisementDto) {
		
		Advertisement advertisement = advertisementMapper.map(advertisementDto);
		advertisement = advertisementRepository.save(advertisement);

		AdvertisementResponse advertisementResponse = AdvertisementResponse.builder()
				.data(advertisementMapper.map(advertisement))
				.meta(metaDataMapper.map(EcomConstant.ADVERTISEMENT_CREATE_SUCCESS_CODE, EcomConstant.ADVERTISEMENT_CREATE_SUCCESS_MESSAGE))
				.build();
		
		return advertisementResponse;
	}
	
	public AdvertisementResponse getAdvertisementById(Long id) {
		Optional<Advertisement> advertisement = advertisementRepository.findById(id);
		if(advertisement.isPresent()) {
			
		}
		Advertisement advertisement2 = advertisement.get();
		AdvertisementResponse advertisementResponse = AdvertisementResponse.builder()
				.data(advertisementMapper.map(advertisement2))
				.meta(metaDataMapper.map(EcomConstant.ADVERTISEMENT_FETCH_CODE, EcomConstant.ADVERTISEMENT_FETCH_MESSAGE))
				.build();
		
		return advertisementResponse;
	} 
	
	public AdvertisementListResponse getAllAdvertisement() {
		List<Advertisement> advertisement = advertisementRepository.findAll();
		
		AdvertisementListResponse advertisementListResponse = AdvertisementListResponse.builder()
				.data(advertisementMapper.map(advertisement))
				.meta(metaDataMapper.map(EcomConstant.ADVERTISEMENT_FETCH_CODE, EcomConstant.ADVERTISEMENT_FETCH_MESSAGE))
				.build();
		
		return advertisementListResponse;
	}
	
	public AdvertisementResponse updateAdvertisement(Long id,AdvertisementDto advertisementDto) {
		
		Optional<Advertisement> advertisement = advertisementRepository.findById(id);
		
		if(advertisement.isPresent()) {
			
		}
		Advertisement advertisement2 = advertisement.get();
		advertisement2 = advertisementMapper.map(advertisementDto);
		advertisement2 = advertisementRepository.save(advertisement2);
		
		AdvertisementResponse advertisementResponse = AdvertisementResponse.builder()
				.data(advertisementMapper.map(advertisement2))
				.meta(metaDataMapper.map(EcomConstant.ADVERTISEMENT_UPDATE_CODE, EcomConstant.ADVERTISEMENT_UPDATE_MESSAGE))
				.build();
		
		return advertisementResponse;
	}
	
	public AdvertisementResponse deleteAdvertisement(Long id) {
		Optional<Advertisement> advertisement = advertisementRepository.findById(id);
		if(!advertisement.isPresent()) {
			
		}
		advertisementRepository.delete(advertisement.get());
		
		return AdvertisementResponse.builder()
				.meta(metaDataMapper.map(EcomConstant.ADVERTISEMENT_DELETE_CODE, EcomConstant.ADVERTISEMENT_DELETE_MESSAGE))
				.build();
	}
	
	public List<Advertisement> getAdvertisementByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<Advertisement> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<Advertisement> pagingUser = advertisementRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
}

