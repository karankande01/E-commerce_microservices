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
import com.ecom.productservice.dto.OfferDto;
import com.ecom.productservice.dto.OfferListResponse;
import com.ecom.productservice.dto.OfferResponse;
import com.ecom.productservice.entity.Offer;
import com.ecom.productservice.mapper.OfferMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.repository.OfferRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OfferService {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private OfferMapper offerMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
public OfferResponse createOffer (OfferDto offerDto) {
	
	System.out.println(offerDto);

		
		Offer offer = offerMapper.map(offerDto);
	    offer =	offerRepository.save(offer);
	    
	    OfferResponse offerResponse = OfferResponse.builder()
	    		.data(offerMapper.map(offer))
	    		.meta(metaDataMapper.map(ProductsConstants.OFFER_SERVICE_SUCCESS_CODE, ProductsConstants.OFFER_SERVICE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return offerResponse;
	}

public OfferResponse getOfferById (Long id) {
	
	Optional<Offer> offer = offerRepository.findById(id);
	
	if(!offer.isPresent()) {
		
	}
	Offer offer2 = offer.get();
	OfferResponse offerResponse = OfferResponse.builder()
			.data(offerMapper.map(offer2))
			.meta(metaDataMapper.map(ProductsConstants.OFFER_SERVICE_FETCH_CODE, ProductsConstants.OFFER_SERVICE_FETCH_MESSAGE))
			.build();
	
	return offerResponse;
}

public OfferListResponse getAllOffer() {
	List<Offer> offers = offerRepository.findAll();
	
	OfferListResponse offerListResponse = OfferListResponse.builder()
			.data(offerMapper.map(offers))
			.meta(metaDataMapper.map(ProductsConstants.OFFER_SERVICE_FETCH_CODE, ProductsConstants.OFFER_SERVICE_FETCH_MESSAGE))
			.build();
	
	return offerListResponse;
}

public OfferResponse updateOffer (Long id, OfferDto offerDto) {
	Optional<Offer> offer = offerRepository.findById(id);
	
	if(!offer.isPresent()) {
		
	}
	
	Offer offer2 = offer.get();
	offer2 = offerMapper.map(offerDto);
	offer2 = offerRepository.save(offer2);
	
	OfferResponse offerResponse =OfferResponse.builder()
			.data(offerMapper.map(offer2))
			.meta(metaDataMapper.map(ProductsConstants.OFFER_SERVICE_UPDATE_CODE, ProductsConstants.OFFER_SERVICE_UPDATE_MESSAGE))
			.build();
	
	return offerResponse;
}

public OfferResponse deleteOffer (Long id) {
	Optional<Offer> offer = offerRepository.findById(id);
	if(!offer.isPresent()) {
		
	}
	
	offerRepository.delete(offer.get());
	 
	 return OfferResponse.builder()
			 .meta(metaDataMapper.map(ProductsConstants.OFFER_SERVICE__DELETE_SUCCESS_CODE, ProductsConstants.OFFER_SERVICE_DELETE_SUCCESS_MESSAGE))
			 .build();
}

public List<Offer> getOfferByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
    
    // Split the searchKeywords into individual terms
    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
    
    // Create a Specification for the search criteria
    Specification<Offer> specification = (root, query, criteriaBuilder) -> {
    	
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

    Page<Offer> pagingUser = offerRepository.findAll(specification,pageRequest);
    return pagingUser.getContent();
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
