package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;



@Entity
	@Table(name="room_types_master")
	public class RoomTypes {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "hotel_id")
		//@JsonBackReference
		private Hotel hotel;
		
//		@OneToMany(mappedBy= "roomtypes",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//		@JsonManagedReference
//		
//		private Set<HotelPrice> hotelprice;
//		
//		public Set<HotelPrice> getHotelprice() {
//			return hotelprice;
//		}
//
//		public void setHotelprice(Set<HotelPrice> hotelprice) {
//			this.hotelprice = hotelprice;
//		}
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "rooms_id")
		//@JsonIgnore
//		@JsonBackReference
		private Rooms rooms;
		
		@OneToMany(mappedBy = "roomtypes",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
		@JsonManagedReference
		private Set<PackageitineraryDetails> packageitineraryDetails;
		
		
 
		
		
//		@OneToMany(mappedBy = "roomtypes", cascade = CascadeType.ALL)
////		// @JsonIgnore
//		@JsonManagedReference
//		private Set<HotelPrice> hotelprice;
		
		
		
		

	   
		public Set<PackageitineraryDetails> getPackageitineraryDetails() {
			return packageitineraryDetails;
		}

		public void setPackageitineraryDetails(Set<PackageitineraryDetails> packageitineraryDetails) {
			this.packageitineraryDetails = packageitineraryDetails;
		}
		@NotBlank(message="bedsize is required")
		 @Column(name="bed_size")
	    private String bedSize;
	    
		//@NotBlank(message="maxperson is required")
	    @Column(name="max_person")
	    private int max_person;
	    
	    //@ElementCollection
	    private String rimage;
	    
	    private boolean status;
		
	    @Column(name="created_date")
	   	private LocalDateTime created_date;
	   	
	    @Column(name="modified_date")
	   	private LocalDateTime modified_date;
	   	
	    @NotBlank(message="created by is required")
	   	@Column(name = "created_by")
	   	private String created_by;
	   	
	    @NotBlank(message="modified by is required")
	   	@Column(name = "modified_by")
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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

//		public Hotel getHotel() {
//			return hotel;
//		}
	//
//		public void setHotel(Hotel hotel) {
//			this.hotel = hotel;
//		}

//		public String getBed_size() {
//			return bed_size;
//		}
//
//		public void setBed_size(String bed_size) {
//			this.bed_size = bed_size;
//		}

		public int getMax_person() {
			return max_person;
		}

		public void setMax_person(int max_person) {
			this.max_person = max_person;
		}

		public Hotel getHotel() {
			return hotel;
		}

		public void setHotel(Hotel hotel) {
			this.hotel = hotel;
		}

		

		public String getRimage() {
			return rimage;
		}

		public void setRimage(String rimage) {
			this.rimage = rimage;
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

		public String getIpaddress() {
			return ipaddress;
		}

		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
		}

		

		
		public String getBedSize() {
			return bedSize;
		}

		public void setBedSize(String bedSize) {
			this.bedSize = bedSize;
		}

		public Rooms getRooms() {
			return rooms;
		}
	//
		public void setRooms(Rooms rooms) {
			this.rooms = rooms;
		}
}
