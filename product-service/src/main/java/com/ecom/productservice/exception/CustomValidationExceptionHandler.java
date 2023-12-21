package com.ecom.productservice.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomValidationExceptionHandler extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public static List<String> extractConstraintViolationMessages(ConstraintViolationException ex) {
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path propertyPath = violation.getPropertyPath();
            String message = violation.getMessage();
            errorMessages.add(propertyPath + ": " + message);
        }
        return errorMessages;
    }
}

