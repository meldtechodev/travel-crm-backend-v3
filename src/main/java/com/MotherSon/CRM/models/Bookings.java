package com.MotherSon.CRM.models;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.PrePersist;

import jakarta.persistence.PreUpdate;

import jakarta.persistence.Table;
 
@Entity
@Table(name = "bookings_master")
public class Bookings {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "query_id")
	private QueryBook query;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "package_id")
	private Pkg pack;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@Column(name = "discount_requested")
	private double discountRequested;

	@Column(name = "final_price")
	private double finalPrice;

	@Column(name = "payment_status")
	private String paymentStatus = "pending";

	@Column(name = "email_sent")
	private boolean emailSent = false;

	private String ipaddress;

	private boolean status = true;

	private boolean isdelete = false;

	@Column(name = "created_by")
	private String createdby;

	@Column(name = "modified_by")
	private String modifiedby;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;


	@PrePersist
    protected void onCreate() {

        createdDate = LocalDateTime.now();

        modifiedDate = LocalDateTime.now();

	}


	 @PreUpdate
	    protected void onUpdate() {

	        modifiedDate = LocalDateTime.now();

	    }
 
 
	public Long getId() {

		return id;

	}
 
 
	public void setId(Long id) {

		this.id = id;

	}
 
 
	public Customer getCustomer() {

		return customer;

	}
 
 
	public void setCustomer(Customer customer) {

		this.customer = customer;

	}
 
 
	public QueryBook getQuery() {

		return query;

	}
 
 
	public void setQuery(QueryBook query) {

		this.query = query;

	}
 
 
	public Pkg getPack() {

		return pack;

	}
 
 
	public void setPack(Pkg pack) {

		this.pack = pack;

	}
 
 
	public Hotel getHotel() {

		return hotel;

	}
 
 
	public void setHotel(Hotel hotel) {

		this.hotel = hotel;

	}
 
 
	public double getDiscountRequested() {

		return discountRequested;

	}
 
 
	public void setDiscountRequested(double discountRequested) {

		this.discountRequested = discountRequested;

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
 
 
	public String getIpaddress() {

		return ipaddress;

	}
 
 
	public void setIpaddress(String ipaddress) {

		this.ipaddress = ipaddress;

	}
 
 
	public boolean isStatus() {

		return status;

	}
 
 
	public void setStatus(boolean status) {

		this.status = status;

	}
 
 
	public boolean isIsdelete() {

		return isdelete;

	}
 
 
	public void setIsdelete(boolean isdelete) {

		this.isdelete = isdelete;

	}
 
 
	public String getCreatedby() {

		return createdby;

	}
 
 
	public void setCreatedby(String createdby) {

		this.createdby = createdby;

	}
 
 
	public String getModifiedby() {

		return modifiedby;

	}
 
 
	public void setModifiedby(String modifiedby) {

		this.modifiedby = modifiedby;

	}
 
 
	public LocalDateTime getCreatedDate() {

		return createdDate;

	}
 
 
	public void setCreatedDate(LocalDateTime createdDate) {

		this.createdDate = createdDate;

	}
 
 
	public LocalDateTime getModifiedDate() {

		return modifiedDate;

	}
 
 
	public void setModifiedDate(LocalDateTime modifiedDate) {

		this.modifiedDate = modifiedDate;

	}
 
}	