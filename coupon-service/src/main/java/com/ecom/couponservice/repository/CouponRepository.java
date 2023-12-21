package com.ecom.couponservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.couponservice.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>,
PagingAndSortingRepository<Coupon, Long>,
JpaSpecificationExecutor<Coupon>{

}
