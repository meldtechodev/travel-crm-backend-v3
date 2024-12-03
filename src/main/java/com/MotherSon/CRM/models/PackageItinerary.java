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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
 
@Entity
	@Table(name = "packageItinerary_master")
	public class PackageItinerary {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//@NotBlank(message = " Day number is required")
		@Column(name = "day_number", nullable = false)
		private int daynumber;
		
		@NotBlank(message = "City Name is requird")
		@Column(name = "city_name", nullable = false)
		private String cityname;
		
		@NotBlank(message = " Day Title is required")
		@Column(name = "day_title" , nullable = false)
		private String daytitle;
		
		@NotBlank(message = "Program is required")
		@Column(name = "program" , nullable = false)
		private String program;
		
		@NotBlank(message = "Meals is required")
		@Column(name = "meals" , nullable = false)
		private String meals;
		
//		//@NotBlank(message = "Transport Id is required")
//		@Column(name = "transport_id" , nullable = false)
//		private Long transportid;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="transport_id")
		private Transport transport;
		
 
//		@ManyToOne(fetch=FetchType.EAGER)
//		@JoinColumn(name="packId")
//		private Pkg packid;
		
		@Column(name="packId")
		private Long packid;
		
		
		
		public Long getPackid() {
			return packid;
		}
 
 
		public void setPackid(Long packid) {
			this.packid = packid;
		}
 
 
		public Transport getTransport() {
			return transport;
		}
 
 
		public void setTransport(Transport transport) {
			this.transport = transport;
		}
 
 
		@NotBlank(message = "Created By is required")
		@Column(name = "created_by" , nullable =  false)
		private String createdby;
		
		private String modifiedby;
		
//		@NotBlank(message = "ipAddress is required")
//		@Column(name = "ipAddress" , nullable = false)
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
 
 
		public int getDaynumber() {
			return daynumber;
		}
 
 
		public void setDaynumber(int daynumber) {
			this.daynumber = daynumber;
		}
 
 
		public String getCityname() {
			return cityname;
		}
 
 
		public void setCityname(String cityname) {
			this.cityname = cityname;
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
 
 
 
	}