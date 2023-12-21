package com.ecom.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecom.user.entity.User;


public interface UserRepository extends JpaRepository<User, Long>,PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {


     Optional<User> findByEmail(String email);
	
	//Optional<UserCheck> findByEmailAndPassword(String email,String password);

}
