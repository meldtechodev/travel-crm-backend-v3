package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//import java.time.LocalDateTime;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
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
	@Table(name="rooms_master")
@JsonIgnoreProperties(value = { "rooms" },allowSetters=true)
	public class Rooms {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)

		private Long id;
		@NotBlank(message="room name is required")
		@Column(name="room_name")
		private String roomname;
		
		
		@OneToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		// @JsonIgnore
//		@JsonManagedReference
		private Set<RoomTypes> roomtypes;
		 
		
		
		private boolean status;
		
		@Column(name = "created_by")
		private String created_by;

		@Column(name = "modified_by")
		private String modified_by;

		private boolean isdelete;
		
		@Column(name="created_date")
		private LocalDateTime createddate;
		
	    
		@Column(name="modified_date")
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
		
		
		
		
		public String getRoomname() {
			return roomname;
		}
		public void setRoomname(String roomname) {
			this.roomname = roomname;
		}

		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
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
