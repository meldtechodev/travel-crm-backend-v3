package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//import java.time.LocalDateTime;
//import java.util.List;
////import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
////import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
////import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;


//import com.ms.jwt.destinationmodels.Destination;

@Entity
@Table(name = "country_master")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// @JsonIgnoreProperties({"states", "destinations"})
@JsonIgnoreProperties(value = { "states", "destinations","hotel" })
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "country name is required")
	@Size(min = 2, max = 30, message = "country name must be between 2 and 30 characters")
	 @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name must contain only alphabets")
	@Column(name = "cname", nullable = false)
	private String countryName;

	@NotBlank(message = "country code name is required")
	@Pattern(regexp = "^[A-Z]{2,3}$", message = "Country code must consist of to uppercase letters and alphabatical order")
	@Column(name = "ccode", nullable = false, unique = true)
	private String code;
     
	@NotBlank(message="country phone pCode required")
	private String pCode;
	
	@NotBlank(message = "Ip address is reqired")
	// @Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP
	// address format")

	@Column(name = "ipAddress", nullable = false)
	private String ipAddress;

//	@Column(name = "master_key", unique = true)
//	private String masterKey;

	
	
	
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	
	@JsonManagedReference
	private Set<State> states;

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Destination> destinations;
	
	
	@OneToMany(mappedBy = "country",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Hotel> hotel;

//	 @ElementCollection
	@Column(name = "cimages")
	private List<String> cimage;

	

	public List<String> getCimage() {
		return cimage;
	}

	public void setCimage(List<String> cimage) {
		this.cimage = cimage;
	}
   
	//@NotBlank(message="status is required")
	private boolean status;

	@Column(name = "created_by")
	private String createdby;

	@Column(name = "modified_by")
	private String modifiedby;

	private boolean isdelete;
	
	@Column(name="created_date")

	private LocalDateTime createddate;
    
	@Column(name="modified_date")
	private LocalDateTime modifieddate;

	@PrePersist
	protected void onCreate() {
		createddate = LocalDateTime.now();
		modifieddate = LocalDateTime.now();

	}

	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}

	public LocalDateTime getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(LocalDateTime modifieddate) {
		this.modifieddate = modifieddate;
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

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	public Set<State> getStates() {
		return states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}

//	@PrePersist
//	@PreUpdate
//	public void generateMasterKey() {
//		this.masterKey = "COUNTRY-" + this.code + "-" + UUID.randomUUID().toString();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Set<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(Set<Hotel> hotel) {
		this.hotel = hotel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

//	public String getMasterKey() {
//		return masterKey;
//	}
//
//	public void setMasterKey(String masterKey) {
//		this.masterKey = masterKey;
//	}
	// Getters and Setters
}
