package com.ecom.user.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.dto.UserDto;
import com.ecom.user.dto.UserListResponse;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.entity.User;
import com.ecom.user.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
@Validated
public class UsersController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("/login")
//	public ResponseEntity<?> userLogin(@RequestBody LoginDto login){
//		return userService.loginUser(login);
//	}
	
	@PostMapping("")
	public ResponseEntity<UserResponse> createUsers (@Valid @RequestBody UserDto usersDto){
	 UserResponse usersResponse =userService.createUsers(usersDto);
	 return ResponseEntity.ok(usersResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUsersById (@PathVariable Long id){
	UserResponse usersResponse =	userService.getUserById(id); 
	return ResponseEntity.ok(usersResponse);
	}
	
	@GetMapping
	public ResponseEntity<UserListResponse> getAllUsers(){
	UserListResponse usersListResponses = userService.getAllUsers();
	return ResponseEntity.ok(usersListResponses);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUsers(@PathVariable Long id, @Valid @RequestBody UserDto usersDto){
		UserResponse usersResponse =userService.updateUsers(id, usersDto);
		return ResponseEntity.ok(usersResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponse> deleteUsers(@PathVariable Long id){
		UserResponse usersResponse = userService.deleteUsers(id);
		return ResponseEntity.ok(usersResponse);
	}
	
//	@GetMapping("/page")
//    public List<Users> getUserWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
//                                        @RequestParam(defaultValue = "10") Integer pageSize,
//                                        @RequestParam String field){
//
//        return usersService.getUsersByPagination(pageNo,pageSize,field);
//
//    }
	
	    @GetMapping("/page")
	    public List<User> getUserWithPaging(
	            @RequestParam(defaultValue = "0") int pageNo,
	            @RequestParam(defaultValue = "10") int pageSize,
	            @RequestParam(required = false) List<String> sortFields,
	            @RequestParam(required = false) String searchKeyword) {

	        return userService.getUsersByPagination(pageNo, pageSize, sortFields,searchKeyword);
	    }  
	
	    @GetMapping("/find/{email}")
	    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
	        Optional<User> userOptional = userService.getUserByEmail(email);
	
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	        }
	    }


}
