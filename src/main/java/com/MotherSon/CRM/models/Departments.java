package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
 
 
@Entity
@Table(name = "departments_master")
@JsonIgnoreProperties(value= {"user"})
public class Departments {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	private Long id;
	
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Department name must only contain alphabetical characters and spaces.")
	@Column(name = "department_Name", nullable = false)
	private String departmentName;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	@JsonIgnore
//@JoinColumn(nullable = true)
@OneToMany(mappedBy = "departments", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	
//	//@JsonManagedReference
	private Set<Designations> designations;
	
	
	
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<User> user;
	
	
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
 
	public String getDepartmentName() {
		return departmentName;
	}
 
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
 
	public String getCreatedBy() {
		return createdBy;
	}
 
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public String getModifiedBy() {
		return modifiedBy;
	}
 
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
 
	public Set<Designations> getDesignations() {
		return designations;
	}
 
	public void setDesignations(Set<Designations> designations) {
		this.designations = designations;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	

}