package com.ecom.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.productservice.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>,
PagingAndSortingRepository<Offer, Long>,
JpaSpecificationExecutor<Offer>{

}
