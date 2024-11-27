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

@Entity
@Table(name="Transport_master")
public class Transport {
 
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	  
	  @NotBlank(message = "Transport mode is require")
	  @Column(name = "transport_mode" , nullable = false)
	  private String transportmode;
	  
	  @NotBlank(message = "Transport Supplier is require")
	  @Column(name = "transport_supplier" , nullable = false)
	  private String transportsupplier;
	  
	 // @NotBlank(message = "Price per day is require")
	  @Column(name = "price_per_day" , nullable = false)
	  private double priceperday;
	  
	  @NotBlank(message = "IpAddress is required")
	  @Column(name = "ipAddress" , nullable = false)
	  private String ipaddress;
	  
	  
	  private boolean status;
	  
	  private boolean isdelete;
	  
	 
	  @NotBlank(message = " Created_by is required")
	  @Column(name = "created_by" , nullable = false)
	  private String createdby;
	  
	  @Column(name = "modified_by" , nullable = false)
	  private String modifiedby;
	  
	  private LocalDateTime created_date;
	  
	  private LocalDateTime modified_date;
	  
	  
	  @PrePersist
	  protected void onCreate() {
		  created_date = LocalDateTime.now();
		  modified_date = LocalDateTime.now();
	  }
	  
	  @PreUpdate
	  protected void onUpdate() {
		  modified_date = LocalDateTime.now();
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransportmode() {
		return transportmode;
	}

	public void setTransportmode(String transportmode) {
		this.transportmode = transportmode;
	}

	public String getTransportsupplier() {
		return transportsupplier;
	}

	public void setTransportsupplier(String transportsupplier) {
		this.transportsupplier = transportsupplier;
	}

	public double getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(double priceperday) {
		this.priceperday = priceperday;
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

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public LocalDateTime getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDateTime modified_date) {
		this.modified_date = modified_date;
	}

}


