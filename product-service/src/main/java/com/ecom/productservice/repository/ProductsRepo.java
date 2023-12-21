package com.ecom.productservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.projection.ProductNameProjection;

public interface ProductsRepo extends JpaRepository<Products, Long>,PagingAndSortingRepository<Products, Long>,
JpaSpecificationExecutor<Products>{

    Optional<List<ProductNameProjection>> findByProductNameContainingIgnoreCase(@Param("name") String name);
	
    @Query("SELECT p FROM Products p WHERE p.launchDate BETWEEN :startDateTime AND :endDateTime ORDER BY p.launchDate DESC")
    List<Products> findProductsLaunchedInTimeRange(
        @Param("startDateTime") LocalDateTime startDateTime,
        @Param("endDateTime") LocalDateTime endDateTime
    );
}
