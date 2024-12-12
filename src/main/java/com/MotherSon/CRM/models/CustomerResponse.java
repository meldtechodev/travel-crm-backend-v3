package com.MotherSon.CRM.models;

public class CustomerResponse {
	
	private String message;
    private Customer customer;

    public CustomerResponse(String message, Customer customer) {
        this.message = message;
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}    
}