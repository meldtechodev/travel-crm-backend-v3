package com.MotherSon.CRM.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="PackageThemeMaster")
public class PackageTheme {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
  private String title;
  
  private String createdby;
  private String modifiedby;
  private LocalDateTime createddate;
  private LocalDateTime modifieddate;
  public String getTitle() {
	return title;
}

  
public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public void setTitle(String title) {
	this.title = title;
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
}
