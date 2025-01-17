package com.MotherSon.CRM.exception;

import com.MotherSon.CRM.dto.CustomException;
import com.MotherSon.CRM.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

// Annotation to handle exceptions globally
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            "FAILED",
            "An unexpected error occurred: " + ex.getMessage(),
            "500"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            "FAILED",
            "A null value was encountered: " + ex.getMessage(),
            "400"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found (404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoHandlerFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            "FAILED",
            "The requested resource was not found: " + ex.getRequestURL(),
            "404"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle custom business logic exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            "FAILED",
            ex.getMessage(),
            ex.getErrorCode()
        );
        HttpStatus status = HttpStatus.BAD_REQUEST; // Default

        // Return appropriate status based on errorCode
        if ("404".equals(ex.getErrorCode())) {
            status = HttpStatus.NOT_FOUND;
        } else if ("500".equals(ex.getErrorCode())) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(errorResponse, status);
    }
}