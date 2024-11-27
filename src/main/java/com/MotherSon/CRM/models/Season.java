package com.MotherSon.CRM.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
	@Table(name="season_master")
	public class Season {
		
		
//		public Set<HotelPrice> getHotelprice() {
//		return hotelprice;
//	}
//
//	public void setHotelprice(Set<HotelPrice> hotelprice) {
//		this.hotelprice = hotelprice;
//	}


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		

		private String seasonName; // Primary Key

	    private LocalDate startDate;
	    private LocalDate endDate;
	    
	    private boolean status;
		
	   	private LocalDateTime created_date;
	   	
	   	private LocalDateTime modified_date;
	   	
	   	private String created_by;
	   	
	   	private String modified_by;
	   	
	   	private boolean isdelete;
	   	
	   	private String ipaddress;
	   	
	   	
	   	@PrePersist
	   	protected void onCreate() {
	   		created_date = LocalDateTime.now();
	   		modified_date = LocalDateTime.now();
	   		
	   	}
	   	
	   	@PreUpdate
	   	protected void onUpdate() {
	   		modified_date = LocalDateTime.now();
	   	
	   	}
	   	

//	   	@OneToMany(mappedBy = "season", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////		// @JsonIgnore
//	    @JsonManagedReference
//		private Set<HotelPrice> hotelprice;
//	   	




		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSeasonName() {
			return seasonName;
		}

		public void setSeasonName(String seasonName) {
			this.seasonName = seasonName;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
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

		public String getCreated_by() {
			return created_by;
		}

		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}

		public String getModified_by() {
			return modified_by;
		}

		public void setModified_by(String modified_by) {
			this.modified_by = modified_by;
		}

		public boolean isIsdelete() {
			return isdelete;
		}

		public void setIsdelete(boolean isdelete) {
			this.isdelete = isdelete;
		}

//		public Set<HotelPrice> getHotelprice() {
//			return hotelprice;
//		}
	//
//		public void setHotelprice(Set<HotelPrice> hotelprice) {
//			this.hotelprice = hotelprice;
//		}

		public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
		}
}
