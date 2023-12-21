package com.ecom.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.wishlist.entity.WishList;

public interface WishlistRepository extends JpaRepository<WishList,Long>,
PagingAndSortingRepository<WishList, Long>,
JpaSpecificationExecutor<WishList>{

}
