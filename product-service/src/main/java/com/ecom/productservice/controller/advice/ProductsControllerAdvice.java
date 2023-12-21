//package com.ecom.productservice.controller.advice;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import com.ecom.productservice.dto.ErrorMetaInformation;
//import com.ecom.productservice.enums.ErrorKeyMapping;
//import com.ecom.productservice.exception.CustomValidationExceptionHandler;
//import com.ecom.productservice.exception.ProductNotFoundException;
//import com.ecom.productservice.exception.ProductsException;
//
//import jakarta.validation.ConstraintViolationException;
//
//@RestControllerAdvice
//public class ProductsControllerAdvice {
//
//	private static final String UNABLE_TO_PROCESS_REQUEST = "Unable to proccess request";
//
//	@Autowired
//	private MessageSource messageSource;
//
//	@ExceptionHandler(ProductsException.class)
//	public ResponseEntity<ErrorMetaInformation> serviceException(ProductsException ex, WebRequest request, @RequestParam(name = "id", required = false) String id) {
//	//	log.error("An error occurred:", ex);
//		ErrorKeyMapping errorKeyMapping = ex.getErrorKeyMapping();
//		String key = errorKeyMapping.getErrorMessageKey();
//		String errorMsg = messageSource.getMessage(key, new Object[]{id}, Locale.getDefault());
//		HttpStatus code = errorKeyMapping.getHttpStatus();
//		return ResponseEntity.status(code).body(ErrorMetaInformation.builder().errorMsg(errorMsg).build());
//	}
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorMetaInformation> exception(Exception ex, WebRequest request) {
//		//log.error("An error occurred:", ex);
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//				ErrorMetaInformation.builder().errorMsg(UNABLE_TO_PROCESS_REQUEST).build());
//	}
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> serviceMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
//	//	log.error("An error occurred:", ex);
//		return ResponseEntity.badRequest().body(ex.getAllErrors());
//	}
//	
//	   @ExceptionHandler(ProductNotFoundException.class)
//	  //  @ResponseStatus(HttpStatus.NOT_FOUND)
//	    public ResponseEntity< Map<String,String>> handleProductNotFoundException(ProductNotFoundException ex) {
//		   Map<String,String> map= new HashMap<>();
//		   map.put("Error-Message", ex.getMessage());
//	        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
//	    }
//	   
//	   @ExceptionHandler(ConstraintViolationException.class)
//	    @ResponseStatus(HttpStatus.BAD_REQUEST)
//	    public ResponseEntity<Object> handleValidationException(ConstraintViolationException ex) {
//	        List<String> errorMessages = CustomValidationExceptionHandler.extractConstraintViolationMessages(ex);
//
//	        Map<String, Object> response = new HashMap<>();
//	        response.put("error", "Validation Failed");
//	        response.put("details", errorMessages);
//
//	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//}
