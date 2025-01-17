package com.MotherSon.CRM.dto;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private String errorCode;

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    

	public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    
    // // Constructor with message, error message, and code
//    private String message;
//    private String errorMessage;
//    private String code;
//    public CustomException(String message, String errorMessage, String code) {
//        super(errorMessage);  // Call the RuntimeException constructor with error message
//        this.message = message;
//        this.errorMessage = errorMessage;
//        this.code = code;
//    }
}