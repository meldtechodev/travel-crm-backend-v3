package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "user_master")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
 
    private String name;
    private String email;
    private String password;
 
    private String createdBy;
    private String modifiedBy;
    private String ipaddress;
 
    private boolean status;
    private boolean isdelete;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="companyid")
    private Company company;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="roleid")
    private Role role;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="department_id")
    private Departments department;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="designationid")
    private Designations designation;
    
    
    
 
  
	
	
 
	
 
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
 
	
 
	
	public Long getUserId() {
		return userId;
	}
 
	public void setUserId(Long userId) {
		this.userId = userId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
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
 
	public Company getCompany() {
		return company;
	}
 
	public void setCompany(Company company) {
		this.company = company;
	}
 
	public Role getRole() {
		return role;
	}
 
	public void setRole(Role role) {
		this.role = role;
	}
 
	public Departments getDepartment() {
		return department;
	}
 
	public void setDepartment(Departments department) {
		this.department = department;
	}
 
	public Designations getDesignation() {
		return designation;
	}
 
	public void setDesignation(Designations designation) {
		this.designation = designation;
	}
 
	
	
	
}