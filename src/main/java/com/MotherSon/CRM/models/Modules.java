package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "modules_Master")
public class Modules {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "parent_Id")
	private Long parentId;
	
	@NotNull(message = "Module name cannot be null.")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Module name must only contain alphabetical characters and spaces.")
	@Column(name = "module_Name")
	private String moduleName;
	
	@Column(name = "created_By")
	private String createdBy;
	
	@Column(name = "modified_By")
	private String modifiedBy;
	
	@Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP address format.")
	@Column(name = "ipAddress")
	private String ipaddress;
	
	
	private boolean status;
	
	private boolean isdelete;
	
	@Column(name = "created_Date")
	private LocalDateTime createdDate;
	
	@Column(name = "modified_Date")
	private LocalDateTime modifiedDate;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "modules", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		private Set<Permissions> permissions;
	
	
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


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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


	public Set<Permissions> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permissions> permissions) {
		this.permissions = permissions;
	}
	
	
	
	
	

}
