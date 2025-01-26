package com.MotherSon.CRM.dto;
 
public class BookingDetailsDTO {
	
	
	private Long id;
	private String detailKey;
	private String detailValue;
	
	private Long bookingsId;
	private double finalPrice;
	private String paymentStatus;
	private boolean emailSent;
	public BookingDetailsDTO(Long id, String detailKey, String detailValue, Long bookingsId, double finalPrice,
			String paymentStatus, boolean emailSent) {
		super();
		this.id = id;
		this.detailKey = detailKey;
		this.detailValue = detailValue;
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
	public String getDetailKey() {
		return detailKey;
	}
	public void setDetailKey(String detailKey) {
		this.detailKey = detailKey;
	}
	public String getDetailValue() {
		return detailValue;
	}
	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
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