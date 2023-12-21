package com.ecom.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.user.constants.EcomConstant;
import com.ecom.user.dto.UserDto;
import com.ecom.user.dto.UserListResponse;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.entity.User;
import com.ecom.user.enums.ErrorKeyMapping;
import com.ecom.user.exceptions.UserServiceException;
import com.ecom.user.mapper.ResponseMetaDataMapper;
import com.ecom.user.mapper.UserMapper;
import com.ecom.user.repository.UserRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private UserMapper usersMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	
//	@Autowired
//	private  PasswordEncoder passwordEncoder;
	
	
//	public UserCheck checkUser(String email) {
//		UserCheck user = (UserCheck) usersRepository.findByEmail(email).orElse(null);
//		return user;
//	}
	
//	public ResponseEntity<?> loginUser(LoginDto login) {
//		System.out.println("Login Details" +login);
//		try {
//			String email = login.getEmail();
//			String password = login.getPassword();
//			
//			System.out.println("Email & Password" + email + password);
//			
//			UserCheck user = usersRepository.findByEmailAndPassword(email, password)
//				  .orElseThrow(null);
//			
//			System.out.println("userrrrr"+ user.getEmail());
//			System.out.println("userrrrr"+ user.getRole());
//
//			Map<String,Object> response = new HashMap<>();
//			response.put("id", user.getUserId());
//			response.put("user_role", user.getRole());
//			response.put("created_at", user.getCreatedAt());
//			response.put("updated_at", user.getUpdatedAt());
//			
//			
//
//			
//			System.out.println("Responsee"+ response);
//			
//			return  userResponse.success(user.getEmail() + " logged in Succefully");
//			
//		} catch (Exception e) {
//		    e.printStackTrace(); // Add this line for debugging
//
//			return userResponse.failed("Invalid Crendentials ");
//		}
//	}

	
	public UserResponse createUsers(UserDto usersDto) {
		
		validateUsers(usersDto);
		//usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
		
		User users = usersMapper.map(usersDto);
	    users =	usersRepository.save(users);
	    
	    UserResponse usersResponse = UserResponse.builder()
	    		.data(usersMapper.map(users))
	    		.meta(metaDataMapper.map(EcomConstant.USERS_CREATE_SUCCESS_CODE, EcomConstant.USERS_CREATE_SUCCESS_MESSAGE))
	    		.build();
	    
	    return usersResponse;
	}
	
	public UserResponse getUserById(Long id) {
	    Optional<User> users =	usersRepository.findById(id);
	    if(!users.isPresent()) {
	    	throw new UserServiceException(ErrorKeyMapping.ECOM_USERSERVICE_DATA_NOT_FOUND);
	    	
	    }
	    User user= users.get();
	    UserResponse usersResponse = UserResponse.builder()
	    		.data(usersMapper.map(user))
	    		.meta(metaDataMapper.map(EcomConstant.USERS_FETCH_CODE, EcomConstant.USERS_FETCH_MESSAGE))
	    		.build();
	    return usersResponse;
	}
	
	public UserListResponse getAllUsers() {
		List<User> users = usersRepository.findAll();
		
		
		UserListResponse usersListResponse = UserListResponse.builder()
				.data(usersMapper.map(users))
				.meta(metaDataMapper.map(EcomConstant.USERS_FETCH_CODE, EcomConstant.USERS_FETCH_MESSAGE))
				.build();
		
		return usersListResponse;
	}
	
	public UserResponse updateUsers(Long id, UserDto usersDto) {
	    Optional<User> user =usersRepository.findById(id);
	    
	    if(!user.isPresent()) {
	    	
	    }
	     User users =user.get();
	     users = usersMapper.map(usersDto);
	     users = usersRepository.save(users);
	     
	     UserResponse usersResponse = UserResponse.builder()
	    		 .data(usersMapper.map(users))
	    		 .meta(metaDataMapper.map(EcomConstant.USERS_UPDATE_CODE, EcomConstant.USERS_UPDATE_MESSAGE))
	    		 .build();
	     
	     return usersResponse;
	     
	}
	
	public UserResponse deleteUsers(Long id) {
	Optional<User> users = usersRepository.findById(id);
	
	if(!users.isPresent()) {
		
	}
	usersRepository.delete(users.get());
	
	return UserResponse.builder()
			.meta(metaDataMapper.map(EcomConstant.USERS_DELETE_CODE, EcomConstant.USERS_DELETE_MESSAGE))
			.build();
	}
	
	public void validateUsers(UserDto usersDto) {
		Optional<User> users = usersRepository.findByEmail(usersDto.getEmail());
		
		System.out.println(usersDto.getEmail());
		
		if(users.isPresent()) {
			throw new UserServiceException(ErrorKeyMapping.ECOM_USERSERVICE_USER_ALREADY_EXIST);
			
		}
	}
	
//	 public List<Users> getUsersByPagination(int pageNo, int pageSize,String field) {
//
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize,Sort.by(field).ascending());
//        Page<Users> pagingUser = usersRepository.findAll(pageRequest);
//        return pagingUser.getContent();
//    }
	
	public List<User> getUsersByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<User> specification = (root, query, criteriaBuilder) -> {
	    	
	        List<Predicate> predicates = new ArrayList<>();

	     // Add predicates for each field and each search term
	        for (String term : searchTerms) {
	            List<Predicate> fieldPredicates = new ArrayList<>();

	            // Add predicates for each field
	            for (String field : fields) {
	                fieldPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + term.toLowerCase() + "%"));
	            }

	            // Combine field predicates with OR
	            predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
	        }

	        // Combine the term predicates with AND
	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    };

	    Page<User> pagingUser = usersRepository.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	
	
   
    public Optional<User> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = usersRepository.findByEmail(email);

        if (userOptional.isPresent()) {
          
            return  userOptional;
        }
        return null;
    }
}
