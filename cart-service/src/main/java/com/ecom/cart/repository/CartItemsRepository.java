package com.ecom.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.cart.entity.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems,Long>, PagingAndSortingRepository<CartItems, Long>,JpaSpecificationExecutor<CartItems>{

}
