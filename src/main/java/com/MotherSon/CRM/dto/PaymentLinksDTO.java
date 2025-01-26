package com.MotherSon.CRM.dto;
 
 
public class PaymentLinksDTO {
	
	private Long id;
	private String paymentLink;
	
	private Long bookingsId;
	private double finalPrice;
	private String paymentStatus;
	private boolean emailSent;
	public PaymentLinksDTO(Long id, String paymentLink, Long bookingsId, double finalPrice, String paymentStatus,
			boolean emailSent) {
		super();
		this.id = id;
		this.paymentLink = paymentLink;
		this.bookingsId = bookingsId;
		this.finalPrice = finalPrice;
		this.paymentStatus = paymentStatus;
		this.emailSent = emailSent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymentLink() {
		return paymentLink;
	}
	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}
	public Long getBookingsId() {
		return bookingsId;
	}
	public void setBookingsId(Long bookingsId) {
		this.bookingsId = bookingsId;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public boolean isEmailSent() {
		return emailSent;
	}
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
	
 
}
 
 