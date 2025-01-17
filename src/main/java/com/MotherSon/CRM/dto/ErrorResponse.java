package com.MotherSon.CRM.dto;

public class ErrorResponse {
    private String status;
    private String message;
    private String errorCode;
	public ErrorResponse(String status, String message, String errorCode) {
		super();
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    
    

}