package com.ecom.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListResponse {
	
	private ResponseMetaData meta;
	private List<UserDto> data;

}
