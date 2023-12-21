package com.ecom.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.shipping.entity.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long>,
PagingAndSortingRepository<Shipping, Long>,
JpaSpecificationExecutor<Shipping>{

}
