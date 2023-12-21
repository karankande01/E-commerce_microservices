package com.ecom.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.productservice.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>,
PagingAndSortingRepository<OrderItem, Long>,
JpaSpecificationExecutor<OrderItem>{

}
