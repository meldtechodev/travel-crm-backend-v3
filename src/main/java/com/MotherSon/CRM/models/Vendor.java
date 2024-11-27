package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vendor_master")
public class Vendor {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "Vendor Name is required")
	@Column(name = "Vendor" , nullable = false )
	private String vendorName;
	
	@NotBlank(message = "Vendor Email id required is ")
	@Column(name = "vendor_Emailid" , nullable = false)
	private String vendorEmail;
	
	@NotBlank(message = "Vendor Contact Number is required")
	@Column(name = "vendor_ContactNo", nullable = false)
	private String vendorContactNo;
	
	@NotBlank(message = "Vendor Address is required ")
	@Column(name = "vendor_Address" , nullable = false)
	private String vendorAddress;
	
	@NotBlank(message = "IpAddress is required")
	@Column(name = "ipAddress" , nullable = false)
	public String ipAddress;
	
	public boolean status;
	
	public boolean isdelete;
	
	@NotBlank(message = "Created by is required")
	@Column(name = "created_by" , nullable = false)
	private String createdby;
	
	private String modifiedby;
	
	private LocalDateTime createdDate;
	
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorContactNo() {
		return vendorContactNo;
	}

	public void setVendorContactNo(String vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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