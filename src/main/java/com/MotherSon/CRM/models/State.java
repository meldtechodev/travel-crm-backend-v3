package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
////import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
////import javax.persistence.JoinColumn;
////import javax.persistence.ManyToOne;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//3import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import net.minidev.json.annotate.JsonIgnore;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties(value = {  "hotel"
})
@Table(name = "state_master")


public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "state name is required")
	@Size(min = 2, max = 30, message = "state name must be between 2 and 30 characters")
	 @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "state name must contain  alphabets")

	@Column(name = "sname", nullable = false)
	private String stateName;

	@NotBlank(message = "State code is required")
	@Pattern(regexp = "^[A-Z]{2,3}$", message = "State code must be 2 to 3 uppercase letters")
	@Column(name="scode")
	private String code;
	
	
	
	




	//@NotBlank(message="status is required")
    private boolean status;
    
private LocalDateTime created_date;
	
	private LocalDateTime modified_date;
	
	private String created_by;
	
	private String modified_by;
	
	private boolean isdelete;
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public LocalDateTime getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDateTime modified_date) {
		this.modified_date = modified_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	@PrePersist
	protected void onCreate() {
		created_date = LocalDateTime.now();
		modified_date = LocalDateTime.now();
		
	}
 
@PreUpdate
	protected void onUpdate() {
		modified_date = LocalDateTime.now();
	}
 

	
	
	//private String status;

	@NotBlank(message = "Ipaddress is required")
	// @Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP
	// address format")
	@Column(name="ipaddresss")
	private String ipAddress;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "country_id", nullable = false)
	// @JsonBackReference
// @JsonIgnore
	
	// @JsonIgnore
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cid",nullable = false)
	//@JsonBackReference
	private Country country;

	//@ElementCollection

	// @Column(name="state_image")
	//@ElementCollection
	 @Column(name="simage")
	private List<String> simage;

	
//	public List<String> getS_image() {
//		return s_image;
//	}
//
//	public void setS_image(List<String> s_image) {
//		this.s_image = s_image;
//	}

//	@Column(name = "master_key", unique = true)
//	private String masterKey;

	// Constructors, Getters, and Setters


//	@PrePersist
//	public void generateMasterKey() {
//		this.masterKey = "STATE-" + this.code + "-" + UUID.randomUUID().toString();
//	}

	public Long getId() {
		return id;
	}

	public List<String> getSimage() {
		return simage;
	}

	public void setSimage(List<String> simage) {
		this.simage = simage;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

//	public String getMasterKey() {
//		return masterKey;
//	}
//
//	public void setMasterKey(String masterKey) {
//		this.masterKey = masterKey;
//	}

		

}
