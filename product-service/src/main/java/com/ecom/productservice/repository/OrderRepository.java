package com.ecom.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.productservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, 
PagingAndSortingRepository<Order, Long>,
JpaSpecificationExecutor<Order>{

}
