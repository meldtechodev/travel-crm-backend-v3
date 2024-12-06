package com.MotherSon.CRM.models;
 
import java.time.LocalDateTime;

import java.util.Optional;
 
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

@Table(name = "designation_permissions_master")

public class DesignationPermissions {


	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)


	private Long id;

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

	@JoinColumn(name = "designations_id")

	private Designations designations;


	@ManyToOne(fetch = FetchType.EAGER)

	@JoinColumn(name = "permissions_id")

	private Permissions permissions;



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
 
 
	public Designations getDesignations() {

		return designations;

	}
 
 
	public void setDesignations(Designations designations) {

		this.designations = designations;

	}
 
 
	public Permissions getPermissions() {

		return permissions;

	}
 
 
	public void setPermissions(Permissions permissions) {

		this.permissions = permissions;

	}
 
 
	

 
	

}

 