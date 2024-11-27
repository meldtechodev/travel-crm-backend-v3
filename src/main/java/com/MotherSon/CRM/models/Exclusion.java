package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Exclusion {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String exclusionname;



	private LocalDateTime createddate;

	private LocalDateTime modifieddate;

	private String createdby;

	private String modifiedby;

	private String ipaddress;

	private boolean status;

	private boolean isdelete;
	@PrePersist
	protected void onCreate() {
		createddate = LocalDateTime.now();
		modifieddate = LocalDateTime.now();

	}

	@PreUpdate
	protected void onUpdate() {
		modifieddate = LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExclusionname() {
		return exclusionname;
	}

	public void setExclusionname(String exclusionname) {
		this.exclusionname = exclusionname;
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

}
