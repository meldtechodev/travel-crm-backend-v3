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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "policy_master")
public class Policy {
	
	
	
   @Id
	
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	 
	@Column(nullable=false)
	@NotBlank(message="policy name is required")
	@Size(max=100,message="policy name should not exceed 100 characters")
	private String policyName;
	
	 
	@NotBlank(message="policy description is required")
	@Column(columnDefinition = "TEXT")
	private String policyDescription;
	
	
	 @NotBlank(message="created by is required")
	 @Column(name = "created_by", nullable = false)
	 private String  createdBy;
	 
	 @Column(name = "modified_by")
	 private String modifiedby;
	 
	 @Column(name = "ipAddress")
	 private String ipaddress;
	 
	 //@NotBlank(message= "status is required")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	
	
	
	
	 
}