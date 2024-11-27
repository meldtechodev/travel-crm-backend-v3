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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Customer_Master")
public class Customer {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	@NotBlank(message = "Salutation is required.")
	private String salutation;
	
	@NotBlank(message = "First name is required.")
	private String fName;
	
	@NotBlank(message = "Last name is required.")
	private String lName;
	
	@NotBlank(message = "Email id is required")
	@Email(message = "Email ID must be a valid email address")
	@Column(name = "email_Id" , nullable = false)
	private String emailId;
	
	@NotBlank(message = "Contact No is required")
	@Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Contact No must be a valid phone number")
	@Column(name = "contact_No" , nullable = false)
	private String 	contactNo;
	
	@NotBlank(message = "Marital status is required.")
	private String marritalStatus;
		
	@NotBlank(message = "Customer type is required.")
	private String customerType;
	
	@NotBlank(message = "Lead source is required.")
	private String leadSource;
	
	@Pattern(regexp = "^[0-9]{12}$", message = "Aadhar number must be 12 digits.")
	private String adharNo;
	
	@Pattern(regexp = "^[A-Z0-9]{9}$", message = "Passport ID must be 9 characters.")
	private String passportId;
	
	@NotBlank(message = "Created by is required.")
	private String createdby;
	
	private String modifiedby;
	
	@NotBlank(message = "IP address is required.")
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", message = "Invalid IP address format.")
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	
	
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
	 
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "user_id" )
	 private User user;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSalutation() {
		return salutation;
	}


	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getMarritalStatus() {
		return marritalStatus;
	}


	public void setMarritalStatus(String marritalStatus) {
		this.marritalStatus = marritalStatus;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getLeadSource() {
		return leadSource;
	}


	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}


	public String getAdharNo() {
		return adharNo;
	}


	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}


	public String getPassportId() {
		return passportId;
	}


	public void setPassportId(String passportId) {
		this.passportId = passportId;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

	 
	 

}
