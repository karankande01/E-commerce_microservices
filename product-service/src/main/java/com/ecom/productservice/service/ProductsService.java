package com.ecom.productservice.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.productservice.constant.ProductsConstants;
import com.ecom.productservice.dto.ProductsDto;
import com.ecom.productservice.dto.ProductsListResponse;
import com.ecom.productservice.dto.ProductsResponse;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.enums.ErrorKeyMapping;
import com.ecom.productservice.exception.ProductNotFoundException;
import com.ecom.productservice.exception.ProductsException;
import com.ecom.productservice.mapper.ProductsMapper;
import com.ecom.productservice.mapper.ResponseMetaDataMapper;
import com.ecom.productservice.projection.ProductNameProjection;
import com.ecom.productservice.repository.ProductsRepo;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepo productsRepo;
	
	@Autowired
    private ProductsMapper productsMapper;
	
	@Autowired
	private ResponseMetaDataMapper metaDataMapper;
	
	
	//	public ProductsResponse createProduct(ProductsDto productsDto, String path,
	//	MultipartFile file) throws IOException {
	//
	//String imagePath = uploadImages(path, file);
	//productsDto.setImagePath(imagePath);
	//
	//Products products = productsMapper.map(productsDto);
	//products = productsRepo.save(products);
	//
	//ProductsResponse productsResponse = ProductsResponse.builder()
	//		.data(productsMapper.map(products))
	//		.meta(metaDataMapper.map(ProductsConstants.PRODUCTS_SERVICE_SUCCESS_CODE, ProductsConstants.PRODUCTS_SERVICE_SUCCESS_MESSAGE))
	//		.build();
	// return productsResponse;
	//}
	
	
	
	public ProductsResponse createProduct(ProductsDto productsDto) {
	//   try {
	        Products products = productsMapper.map(productsDto);
	        products = productsRepo.save(products);
	        ProductsResponse productsResponse = ProductsResponse.builder()
	                .data(productsMapper.map(products))
	                .meta(metaDataMapper.map(ProductsConstants.PRODUCTS_SERVICE_SUCCESS_CODE, ProductsConstants.PRODUCTS_SERVICE_SUCCESS_MESSAGE))
	                .build();
	        return productsResponse;
//	    } catch (ProductsException e) {
//          throw new ProductsException(ErrorKeyMapping.PRODUCT_SERVICE_DATA_CREATION_FAILED);
//	 }
 }			
	
	public String uploadImages(String path, MultipartFile file) throws java.io.IOException {	
	String name= file.getOriginalFilename();	
    String randomId = UUID.randomUUID().toString();	 
	String fileName1=	randomId.concat(name.substring(name.lastIndexOf("")));
	String filePath =	path + File.separator + fileName1;
	
	File f = new File(path);
	if(!f.exists()) {
		f.mkdir();
	}	
		Files.copy(file.getInputStream(),Paths.get(filePath));	
		return filePath;
	}	
	
	public ProductsResponse getProductById(long productId) {
		Optional<Products> product = productsRepo.findById(productId);
		if (!product.isPresent()) {
			//throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
			throw new ProductNotFoundException("product Not Found");
		}
		Products existProduct  = product.get();
		ProductsResponse productResponse = ProductsResponse.builder()
				.data(productsMapper.map(existProduct))
				.meta(metaDataMapper.map(ProductsConstants.PRODUCT_SERVICE_FETCH_CODE,
						ProductsConstants.PRODUCT_SERVICE_FETCH_MESSAGE))
				.build();
		return productResponse;
	}
	
//	public ProductsListResponse getAllProducts() {
//		Optional<List<Products>> productList = Optional.ofNullable(productsRepo.findAll());
//
//	    if (productList.isEmpty()) 
//	       // throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
//	    	throw new ProductNotFoundException("product Not Found");
//
//	    ProductsListResponse productsListResponse = ProductsListResponse.builder()
//	            .data(productsMapper.map(productList.get()))
//	            .meta(metaDataMapper.map(ProductsConstants.PRODUCT_SERVICE_FETCH_CODE,
//	                    ProductsConstants.PRODUCT_SERVICE_FETCH_MESSAGE))
//	            .build();
//	    return productsListResponse;
//	  }
	
	public ProductsListResponse getAllProducts(int pageNumber, int pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
			
		Page<Products> pageList = productsRepo.findAll(p);
		Optional<List<Products>> productList = Optional.ofNullable(pageList.getContent());
		
		//Optional<List<Products>> productList = Optional.ofNullable(productsRepo.findAll(p));

	    if (productList.isEmpty()) 
	       // throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
	    	throw new ProductNotFoundException("product Not Found");

	    ProductsListResponse productsListResponse = ProductsListResponse.builder()
	            .data(productsMapper.map(productList.get()))
	            .meta(metaDataMapper.map(ProductsConstants.PRODUCT_SERVICE_FETCH_CODE,
	                    ProductsConstants.PRODUCT_SERVICE_FETCH_MESSAGE))
	            .build();
	    return productsListResponse;
	  }
	

    public ProductsResponse deleteProducts(long id) {
        Optional<Products> optionalProducts = productsRepo.findById(id);

        if (optionalProducts.isEmpty())
           // throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
        	throw new ProductNotFoundException("product Not Found");
        
        productsRepo.delete(optionalProducts.get());
        return ProductsResponse.builder().meta(metaDataMapper.map(ProductsConstants.PRODUCTS_SERVICE__DELETE_SUCCESS_CODE,
                ProductsConstants.PRODUCTS_SERVICE_DELETE_SUCCESS_MESSAGE)).build();
    }
    
    
    public ProductsResponse updateProducts(long id, ProductsDto productsDto) {
        Optional<Products> optionalProducts = productsRepo.findById(id);

        if (!optionalProducts.isPresent())
            //throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
        	throw new ProductNotFoundException("product Not Found");
        Products products = optionalProducts.get();
        products = productsMapper.map(productsDto);
        
        System.out.println(products.getProductId());
        
        products = productsRepo.save(products);

        ProductsResponse productsResponse = ProductsResponse.builder()
                .data(productsMapper.map(products))
                .meta(metaDataMapper.map(ProductsConstants.PRODUCTS_SERVICE_UPDATE_CODE,
                		ProductsConstants.PRODUCTS_SERVICE_UPDATE_MESSAGE))
                .build();
        return productsResponse;
    }
    
    public List<Products> getUsersByPagination(int pageNo, int pageSize)           {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Products> pagingUser =productsRepo.findAll(pageRequest);
        return pagingUser.getContent();
   }

    public List<ProductNameProjection> searchProductsByName(String name) {
    	Optional<List<ProductNameProjection>> productNameProjection = productsRepo.findByProductNameContainingIgnoreCase(name);
    	if(productNameProjection.isEmpty())
    		throw new ProductNotFoundException("Products Not Found");
    	
    	return productNameProjection.get();
    }
    
//	public ProductsListResponse searchProductsByName(String searchName) {	
//		Optional<List<Products>> productList = productsRepo.findByProductNameContainingIgnoreCase(searchName);
//		if (!productList.isPresent()) 
//			throw new ProductNotFoundException("product Not Found");
//			//throw new ProductsException(ErrorKeyMapping.PRODUCTS_SERVICE_DATA_NOT_FOUND);
//		
//		ProductsListResponse productsListResponse = ProductsListResponse.builder()
//	            .data(productsMapper.map(productList.get()))
//				.meta(metaDataMapper.map(ProductsConstants.PRODUCT_SERVICE_FETCH_CODE,
//						ProductsConstants.PRODUCT_SERVICE_FETCH_MESSAGE))
//				.build();
//		return productsListResponse;
//	}
    
    
    public List<Products> getProductsByPagination(int pageNo, int pageSize, List<String> fields,String searchKeyword) {
	    Sort sort = Sort.by(fields.toArray(new String[0])).ascending();
	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    
	    // Split the searchKeywords into individual terms
	    List<String> searchTerms = Arrays.asList(searchKeyword.split(","));
	    
	    // Create a Specification for the search criteria
	    Specification<Products> specification = (root, query, criteriaBuilder) -> {
	    	
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

	    Page<Products> pagingUser = productsRepo.findAll(specification,pageRequest);
	    return pagingUser.getContent();
	}
	
//    public Optional<Products> getLatestLaunchProductInTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//        return productsRepo.findLatestLaunchProductInTimeRange(startDateTime, endDateTime);
//    }
    
    public List<Products> getProductsLaunchedInTimeRange(String timeRange) {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate;

        switch (timeRange.toLowerCase()) {
            case "week":
                startDate = endDate.minus(1, ChronoUnit.WEEKS);
                break;
            case "month":
                startDate = endDate.minus(1, ChronoUnit.MONTHS);
                break;
            default:
                throw new IllegalArgumentException("Invalid time range. Supported values are 'week' or 'month'.");
        }

        return productsRepo.findProductsLaunchedInTimeRange(startDate, endDate);
    }
}