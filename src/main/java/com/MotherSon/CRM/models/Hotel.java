package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
@JsonIgnoreProperties(value = { "roomtypes" 
})
@Entity
	@Table(name = "hotel_master")
	public class Hotel {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "c_id", nullable = false)
		//@JsonBackReference
		private Country country;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "s_id")
		//@JsonBackReference
		private State state;
		
//		@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////		// @JsonIgnore
//		@JsonManagedReference
//		private Set<Hotel> hotelprice;
//		
//		
//		public Set<Hotel> getHotelprice() {
//			return hotelprice;
//		}
//
//
//		public void setHotelprice(Set<Hotel> hotelprice) {
//			this.hotelprice = hotelprice;
//		}
//





		@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		// @JsonIgnore
		@JsonManagedReference
		private Set<RoomTypes> roomtypes;
		
		
//		@OneToMany(mappedBy= "hotel",fetch=FetchType.LAZY,cascade= CascadeType.ALL)
//		 @JsonManagedReference
//		private Set<HotelPrice>hotelprice;
//		
		


//		public Set<HotelPrice> getHotelprice() {
//			return hotelprice;
//		}
//
//
//		public void setHotelprice(Set<HotelPrice> hotelprice) {
//			this.hotelprice = hotelprice;
//		}


		public Set<RoomTypes> getRoomtypes() {
			return roomtypes;
		}


		public void setRoomtypes(Set<RoomTypes> roomtypes) {
			this.roomtypes = roomtypes;
		}

		
		

		
		
//		public Season getSeason() {
//			return season;
//		}
	//
	//
//		public void setSeason(Season season) {
//			this.season = season;
//		}
      
		
		
		@Size(min = 2, max = 70, message = " hotel name must be between 2 and 70 characters")
		
		@NotBlank(message = "Hotel name is required")
		//@Column
		private String hname;
		
        
		@Size(min = 2, message = "hotel description minimum 2 characters")
		@NotBlank(message = "hoteldescription is required name is required")
		private String hdescription;
		
		 @NotBlank(message="hotel rating is required")
		private String star_ratings;
		
		//@ElementCollection
		private List<String> himage;
		
		@NotBlank(message = "hotel contact name is required ")
		private String hcontactname;
		
		   @NotBlank(message = "Hotel contact number is required")
		    @Pattern(regexp = "\\d+", message = "Hotel contact number must be numeric")
		private String hcontactnumber;
		
		 @NotBlank(message = "Hotel contact email is required")
		    @Email(message = "Invalid email format")
		private String hcontactemail;
		
		 @NotBlank(message = "Hotel address is required")
		private String haddress;
		 
		 @NotBlank(message = "Hotel pincode is required")
		    
		private String hpincode;
		
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
		
		 @NotBlank(message = "Created by is required")
		private String created_by;
		
		 @NotBlank(message = "modified_by by is required")
		private String modified_by;
		
		private String ipaddress;
		
		private boolean status;
		
		private boolean isdelete;
		
		
		
		
		
	   @ManyToOne(fetch = FetchType.EAGER)
	   @JoinColumn(name = "d_id")
	  // @JsonBackReference
	  private Destination destination;

	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	//public Country getCountry() {
//		return country;
	//}
	//
	//
	//public void setCountry(Country country) {
//		this.country = country;
	//}
	//
	//
	//public State getState() {
//		return state;
	//}
	//
	//
	//public void setState(State state) {
//		this.state = state;
	//}


	public String getHname() {
		return hname;
	}


	public void setHname(String hname) {
		this.hname = hname;
	}


	public String getHdescription() {
		return hdescription;
	}


	public void setHdescription(String hdescription) {
		this.hdescription = hdescription;
	}


	public String getStar_ratings() {
		return star_ratings;
	}


	public void setStar_ratings(String star_ratings) {
		this.star_ratings = star_ratings;
	}


	public List<String> getHimage() {
		return himage;
	}


	public void setHimage(List<String> himage) {
		this.himage = himage;
	}


	public String getHcontactname() {
		return hcontactname;
	}


	public void setHcontactname(String hcontactname) {
		this.hcontactname = hcontactname;
	}


	public String getHcontactnumber() {
		return hcontactnumber;
	}


	public void setHcontactnumber(String hcontactnumber) {
		this.hcontactnumber = hcontactnumber;
	}


	public String getHcontactemail() {
		return hcontactemail;
	}


	public void setHcontactemail(String hcontactemail) {
		this.hcontactemail = hcontactemail;
	}


	public String getHaddress() {
		return haddress;
	}


	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}


	public String getHpincode() {
		return hpincode;
	}


	public void setHpincode(String hpincode) {
		this.hpincode = hpincode;
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


	public Destination getDestination() {
		return destination;
	}


	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}
