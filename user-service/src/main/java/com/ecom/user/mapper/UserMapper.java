package com.ecom.user.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecom.user.dto.UserDto;
import com.ecom.user.entity.User;


@Component
public class UserMapper extends ModelMapper {
	
	public User map(UserDto userDto) {
		return this.map(userDto, User.class);
	}
	
	public UserDto map(User user) {
		return this.map(user,UserDto.class);
	}
	
	public List<UserDto> map(List<User> usersList){
		return usersList.stream().map(entity->this.map(entity)).collect(Collectors.toList());
	}
    
	public User map(UserDto userDto, User user) {
		return this.map(userDto, User.class);
	}

}
