package com.ecom.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
	
	private ResponseMetaData meta;
	private UserDto data;

}
