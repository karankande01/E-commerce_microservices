package com.ecom.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.productservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>,
PagingAndSortingRepository<Category, Long>,
JpaSpecificationExecutor<Category>{

}
