package com.ecom.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.productservice.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>,
PagingAndSortingRepository<Brand, Long>,
JpaSpecificationExecutor<Brand>{

}
