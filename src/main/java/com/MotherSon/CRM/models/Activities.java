package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "activities_master")
public class Activities {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	
	private Long id;
	
	
	
	
	@NotBlank(message = "Title is required")
	@Column(name = "title" , nullable = false)
	private String title;
	
	
	@NotBlank(message = "IpAddress is required ")
	@Column(name = " ipAddress")
	private String ipaddress;
	
	@Column(name = "status" , nullable = false)
	private boolean status;
	
	private boolean isdelete;
	
	@NotBlank(message = "Created_By is required")
	@Column(name = "created_by" , nullable = false)
	private String createdby;
	
	@Column(name = "modified_by")
	private String modifiedby;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
//	@ManyToMany(mappedBy = "activities")
//    private Set<PackageitineraryDetails> packageitineraryDetails = new HashSet<>();
	
	
	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
		modifiedDate = LocalDateTime.now();
	}
	
	
	@PreUpdate
	protected void onUpdate() {
		modifiedDate = LocalDateTime.now();
	}

	
	
	
	

//	public Set<PackageitineraryDetails> getPackageitineraryDetails() {
//		return packageitineraryDetails;
//	}
//
//
//	public void setPackageitineraryDetails(Set<PackageitineraryDetails> packageitineraryDetails) {
//		this.packageitineraryDetails = packageitineraryDetails;
//	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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