package com.ecom.productservice.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ecom.productservice.dto.ProductsDto;
import com.ecom.productservice.dto.ProductsListResponse;
import com.ecom.productservice.dto.ProductsResponse;
import com.ecom.productservice.entity.Products;
import com.ecom.productservice.projection.ProductNameProjection;
import com.ecom.productservice.service.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
	
	@Value("${project.image}")
	private String path;
	
	
	@Autowired
	private ProductsService productsService;
	
    @PostMapping("")
    public ResponseEntity<ProductsResponse> createProducts(@Valid @RequestBody ProductsDto productsDto) {
        ProductsResponse productsResponse = productsService.createProduct(productsDto);
        return ResponseEntity.ok(productsResponse);
    }
	
//	@PostMapping("")
//	public ResponseEntity<ProductsResponse> createProduct(@RequestPart("productsDto") ProductsDto productsDto,
//			@RequestPart("file") MultipartFile file) throws IOException {
//      ProductsResponse productsResponse = productsService.createProduct(productsDto,path,file);
//      return ResponseEntity.ok(productsResponse);
//  }
	
	@PostMapping("/upload")
	public String upload( @RequestParam("images") MultipartFile images) throws IOException{
		String fileName = productsService.uploadImages(path, images);
	    return fileName;
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponse> getProductById(@PathVariable Integer id) {
    	ProductsResponse productsResponse = productsService.getProductById(id);
        return ResponseEntity.ok(productsResponse);
    }    
      
  @GetMapping("")
  public ResponseEntity<ProductsListResponse> getAllUserProducts(
		  @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
		  @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
  	 ProductsListResponse productsListResponse = productsService.getAllProducts(pageNumber,pageSize);
      return ResponseEntity.ok(productsListResponse);
  }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductsResponse> deleteProduct(@PathVariable long id) {
    	ProductsResponse productsResponse = productsService.deleteProducts(id);
        return ResponseEntity.ok(productsResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductsResponse> productUpdate(@PathVariable long id,@Valid @RequestBody ProductsDto productsDto) {
    	ProductsResponse productsResponse = productsService.updateProducts(id,productsDto);
        return ResponseEntity.ok(productsResponse);
    }
    
    @GetMapping("/search")
    public List<ProductNameProjection> searchProductsByName(
            @RequestHeader("name") String name) {
        return productsService.searchProductsByName(name);
    }
    
    @GetMapping("/page")
    public List<Products> getProductWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestHeader HttpHeaders headers) {
    	String searchKeyword = headers.getFirst("searchKeyword");

        return productsService.getProductsByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  
    
    @GetMapping("/latest-launch")
    public ResponseEntity<List<Products>> getProductsLaunchedInTimeRange(
            @RequestHeader("timeRange") String timeRange) {
        List<Products> products = productsService.getProductsLaunchedInTimeRange(timeRange);
        return ResponseEntity.ok(products);
    }
}