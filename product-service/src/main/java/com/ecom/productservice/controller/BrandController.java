package com.ecom.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ecom.productservice.dto.BrandDto;
import com.ecom.productservice.dto.BrandListResponse;
import com.ecom.productservice.dto.BrandResponse;
import com.ecom.productservice.entity.Brand;
import com.ecom.productservice.service.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brand")
@Validated
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@PostMapping
	public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandDto brandDto){
		BrandResponse brandResponse = brandService.createBrand(brandDto);
		return ResponseEntity.ok(brandResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {
		BrandResponse brandResponse = brandService.getBrandById(id);
		return ResponseEntity.ok(brandResponse);
	}
	
	@GetMapping
	public ResponseEntity<BrandListResponse> getAllBrand(){
		BrandListResponse brandListResponse = brandService.getAllBrand();
		return ResponseEntity.ok(brandListResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BrandResponse> updateBrand(@PathVariable Long id,@Valid @RequestBody BrandDto brandDto){
		BrandResponse brandResponse = brandService.updateBrand(id, brandDto);
		return ResponseEntity.ok(brandResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BrandResponse> deleteBrand(@PathVariable Long id) {
		BrandResponse brandResponse = brandService.deleteBrand(id);
		return ResponseEntity.ok(brandResponse);
	}
	
	@GetMapping("/page")
    public List<Brand> getBrandWithPaging(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) List<String> sortFields,
            @RequestParam(required = false) String searchKeyword) {

        return brandService.getBrandByPagination(pageNo, pageSize, sortFields,searchKeyword);
    }  


}
