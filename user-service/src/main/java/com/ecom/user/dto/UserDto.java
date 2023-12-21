package com.ecom.user.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ecom.user.entity.Address;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
	
		
		@Column(name = "user_id")
		public long userId;
		
		@Column(name = "username")
		public String userName;
		
		@Column(name = "password")
		public String password;
		
		@Column(name = "email")
		public String email;
		
		@Column(name = "first_name")
		public String firstName;
		
		@Column(name = "last_name")
		public String lastName;
		
		public List<Address> address = new ArrayList<Address>();
		
		@Column(name = "phone_number")
		public String phoneNumber;
		
		@Column(name = "role")
		public String role;
		
		@Column(updatable = false)
		@CreationTimestamp
		private LocalDateTime createdAt;

		@UpdateTimestamp
		private LocalDateTime updatedAt;
	}



