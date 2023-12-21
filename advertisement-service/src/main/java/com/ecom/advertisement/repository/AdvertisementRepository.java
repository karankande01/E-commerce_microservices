package com.ecom.advertisement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.advertisement.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>,
PagingAndSortingRepository<Advertisement, Long>,
JpaSpecificationExecutor<Advertisement>{

}
