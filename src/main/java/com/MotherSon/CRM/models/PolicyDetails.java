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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="package_policy_details")
public class PolicyDetails {
     
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String policytitle;
	
	@Column(columnDefinition = "TEXT")
	private String policydescription;
	private String createdby;

	private String modifiedby;
	
	private String ipaddress;

	private LocalDateTime createddate;

	private LocalDateTime modifieddate;

	private boolean status;

	private boolean isdelete;
	@PrePersist
	protected void oncreate() {
		createddate = LocalDateTime.now();
		modifieddate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onupdate() {
		modifieddate = LocalDateTime.now();

	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="policyid")
	private Policy policy;
	
	@ManyToOne
	@JoinColumn(name="packItId")
	private PackageItinerary packitid;
	public String getPolicytitle() {
		return policytitle;
	}

	public void setPolicytitle(String policytitle) {
		this.policytitle = policytitle;
	}

	public String getPolicydescription() {
		return policydescription;
	}

	public void setPolicydescription(String policydescription) {
		this.policydescription = policydescription;
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public PackageItinerary getPackitid() {
		return packitid;
	}

	public void setPackitid(PackageItinerary packitid) {
		this.packitid = packitid;
	}
	
	
	
	
}
