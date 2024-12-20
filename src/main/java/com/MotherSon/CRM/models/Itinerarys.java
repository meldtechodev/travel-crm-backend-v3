package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "itinerarys_master")
public class Itinerarys {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "did", nullable = false)
//    @JsonBackReference(value = "destinationReference") // Must match the Destination's @JsonManagedReference
//    private Destination destination;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activitiesid" , nullable = false)
	//@JsonBackReference
	private Activities activities;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sightseeingid" , nullable = false)
	//@JsonBackReference
	private Sightseeing sightseeing;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "did" , nullable = false)
	
	private Destination destination;
	
	@Column(name="hotelOptionid")
	private String hoteloptionid;
	
	 @Transient
	    private List<Long> hotelOptionIds;  // Temporary field to capture JSON array input

	    // Convert List<Long> to comma-separated String when setting hotelOptionIds
	    public void setHotelOptionIds(List<Long> hotelOptionIds) {
	        this.hotelOptionIds = hotelOptionIds;
	        this.hoteloptionid = hotelOptionIds.stream()
	                .map(String::valueOf)
	                .collect(Collectors.joining(","));
	    }

	    // Convert comma-separated String back to List<Long> for easy access
	    public List<Long> getHotelOptionIds() {
	        if (hoteloptionid == null || hoteloptionid.isEmpty()) {
	            return List.of();
	        }
	        return List.of(hoteloptionid.split(","))
	                .stream()
	                .map(Long::valueOf)
	                .collect(Collectors.toList());
	    }

	
	public String getHoteloptionid() {
		return hoteloptionid;
	}


	public void setHoteloptionid(String hoteloptionid) {
		this.hoteloptionid = hoteloptionid;
	}


	@NotBlank(message = " Day Title is required")
	@Column(name = "day_title" , nullable = false)
	private String daytitle;
	
	@NotBlank(message = "Program is required")
	@Column(name = "program", nullable = false, columnDefinition = "TEXT")
	private String program;
	
	@NotBlank(message = "Meals is required")
	@Column(name = "meals" , nullable = false)
	private String meals;
	

	
	@NotBlank(message = "Created By is required")
	@Column(name = "created_by" , nullable =  false)
	private String createdby;
	
	private String modifiedby;
	
	@NotBlank(message = "ipAddress is required")
	@Column(name = "ipAddress" , nullable = false)
	private String ipaddress;
	
	private boolean status;
	
	private boolean isdelete;
	
	private LocalDateTime createddate;
	
	private LocalDateTime modifieddate;
	
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




	public String getDaytitle() {
		return daytitle;
	}


	public void setDaytitle(String daytitle) {
		this.daytitle = daytitle;
	}


	public String getProgram() {
		return program;
	}


	public void setProgram(String program) {
		this.program = program;
	}


	public String getMeals() {
		return meals;
	}


	public void setMeals(String meals) {
		this.meals = meals;
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


	public Activities getActivities() {
		return activities;
	}


	public void setActivities(Activities activities) {
		this.activities = activities;
	}


	public Sightseeing getSightseeing() {
		return sightseeing;
	}


	public void setSightseeing(Sightseeing sightseeing) {
		this.sightseeing = sightseeing;
	}


	public Destination getDestination() {
		return destination;
	}


	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}