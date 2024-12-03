package com.MotherSon.CRM.models;
 
import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
 
@Entity
@Table(name = "designations_master")
@JsonIgnoreProperties(value= {"user"})
public class Designations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Designation name must only contain alphabetical characters and spaces.")
	@Column(name = "designation_Name", nullable = false)
	private String designationName;
	
	@Column(name = "created_By")
	private String createdBy;
	
	@Column(name = "modified_By")
	private String modifiedBy;
	
	private String ipAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id",nullable = true)
	private Departments departments;
	
	
	
	@JsonIgnore
    @OneToMany(mappedBy = "designations", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<DesignationPermissions> designationPermission;
	
	
	
	@JsonManagedReference
	@OneToOne(mappedBy="designation",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private User user;
	
	private boolean status;
	
	private boolean isdelete;
	
	
	@Column(name = "created_Date")
	private LocalDateTime createdDate;
	
	@Column(name = "modified_Date")
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
 
	
	
 
	public String getDesignationName() {
		return designationName;
	}
 
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
 
	public Departments getDepartments() {
		return departments;
	}
 
	public void setDepartments(Departments departments) {
		this.departments = departments;
	}
 
	public Set<DesignationPermissions> getDesignationPermission() {
		return designationPermission;
	}
 
	public void setDesignationPermission(Set<DesignationPermissions> designationPermission) {
		this.designationPermission = designationPermission;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
 
}
 