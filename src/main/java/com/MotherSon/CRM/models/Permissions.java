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
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "permissions_master")
public class Permissions {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Permission name must only contain alphabetical characters and spaces.")
	@Column(name = "permission_Name", nullable = false)
	private String permissionName;
	
	@Column(name = "created_By")
	private String createdBy;
	
	@Column(name = "modified_By")
	private String modifiedBy;
	
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	@Column(name = "created_Date")
	private LocalDateTime createdDate;
	
	@Column(name = "modified_Date")
	private LocalDateTime modifiedDate;
	
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modules_id",nullable = true)
	private Modules modules;
	
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


	public String getPermissionName() {
		return permissionName;
	}


	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
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


	public Modules getModules() {
		return modules;
	}


	public void setModules(Modules modules) {
		this.modules = modules;
	}
	
	

}