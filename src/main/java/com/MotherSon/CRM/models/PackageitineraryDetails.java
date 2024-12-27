package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "packageitinerary_Details")
public class PackageitineraryDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "IpAddress is required")
	@Column(name = "ipAddress" , nullable = false)
	private String ipaddress;
	
	//@Column(name = "status" , nullable = false)
	private boolean status;
	
	
	private String category;
	
	
	
	public String getSightseeingid() {
		return sightseeingid;
	}


	public void setSightseeingid(String sightseeingid) {
		this.sightseeingid = sightseeingid;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="packitid")
	private PackageItinerary packitid;
	
	public PackageItinerary getPackitid() {
		return packitid;
	}


	public void setPackitid(PackageItinerary packitid) {
		this.packitid = packitid;
	}
	
	
	
	@Column(name="mealspackageid")
	private String mealspackageid;
	
	
	@Transient
	 private List<Long> mealspackageIds;
	
	public void setMealspackageIds(List<Long> mealspackageIds) {
	    this.mealspackageIds = mealspackageIds;
	    this.mealspackageid = mealspackageIds.stream()
	            .map(String::valueOf)
	            .collect(Collectors.joining(","));
	}
	
	
	
	public List<Long> getMealspackageIds() {
	    if (mealspackageid == null || mealspackageid.isEmpty()) {
	        return List.of();
	    }
	    return List.of(mealspackageid.split(","))
	            .stream()
	            .map(Long::valueOf)
	            .collect(Collectors.toList());
	}
	
	
	
	

	
	@Column(name="activitiesid")
	private String activitiesid;
	
	
	@Transient
	 private List<Long> activitiesIds;
	
	public void setActivitiesIds(List<Long> activitiesIds) {
	    this.activitiesIds = activitiesIds;
	    this.activitiesid = activitiesIds.stream()
	            .map(String::valueOf)
	            .collect(Collectors.joining(","));
	}
	
	
	
	public List<Long> getActivitiesIds() {
	    if (activitiesid == null || activitiesid.isEmpty()) {
	        return List.of();
	    }
	    return List.of(activitiesid.split(","))
	            .stream()
	            .map(Long::valueOf)
	            .collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	

	 @Column(name="sightseeingid")
	 private String sightseeingid;
	 
	 @Transient
	 private List<Long> sightseeingIds;
	 
	 
	 public void setSightseeingIds(List<Long> sightseeingIds) {
		    this.sightseeingIds = sightseeingIds;
		    this.sightseeingid = sightseeingIds.stream()
		            .map(String::valueOf)
		            .collect(Collectors.joining(","));
		}

		// Getter for sightseeingIds that converts the comma-separated String back to List<Long>
		public List<Long> getSightseeingIds() {
		    if (sightseeingid == null || sightseeingid.isEmpty()) {
		        return List.of();
		    }
		    return List.of(sightseeingid.split(","))
		            .stream()
		            .map(Long::valueOf)
		            .collect(Collectors.toList());
		}
	
  

	@Column(name = "isdelete", nullable = false)
	private boolean isdelete;
	
	@NotBlank(message = "Created By is required")
	@Column(name = "created_by" , nullable = false)
	private String createdby;
	
	@Column(name = "modified_by" , nullable = false)
	private String modifiedby;
	
//	@ManyToOne
//	@JoinColumn(name = "roomtypesid")
//	private RoomTypes roomtypes;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "roomtypesid")
	@JsonManagedReference
	private RoomTypes roomtypes;
 
	

	private LocalDateTime createdDate;
	
	//@Column(name = "modified_Date", nullable = false)
	private LocalDateTime modifiedDate;
	
	
	@PrePersist
	protected void onCreated() {
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
	
	


	public RoomTypes getRoomtypes() {
		return roomtypes;
	}


	public void setRoomtypes(RoomTypes roomtypes) {
		this.roomtypes = roomtypes;
	}


	public String getActivitiesid() {
		return activitiesid;
	}


	public void setActivitiesid(String activitiesid) {
		this.activitiesid = activitiesid;
	}


	

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getMealspackageid() {
		return mealspackageid;
	}


	public void setMealspackageid(String mealspackageid) {
		this.mealspackageid = mealspackageid;
	}

	
	
}