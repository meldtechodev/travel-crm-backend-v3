package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import jakarta.validation.constraints.Size;



@Entity
@Table(name="Destination_master")
@JsonIgnoreProperties(value = {  
		})
public class Destination {
   
	private static final String code = null;
	 
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message="Destination is required")
	@Size(max=200,message = "Destination should not exceed 200 characters")
	@Column(name="dname")
	private String destinationName;

//	@NotBlank(message="descripation is required")
//	@Size(max=300,message = "Description should not exceed 300 characters")
//	@Column(name="Descripation")
//	private String descripation;
	
	private String ipaddress;
 
 
	// @ElementCollection
	// @NotEmpty(message= "atlest one keyAttraction is required")
	
	// @CollectionTable(name = "destination_key_attractions", joinColumns = @JoinColumn(name = "destination_id"))
	// @MapKeyColumn(name = "key_attraction")
	   // @Column(name = "master_key")
	//   @Column(name = "key_attraction")
	//@NotBlank(message="status is required")
     private boolean status;
	
	private LocalDateTime created_date;
	
	private LocalDateTime modified_date;
	
	private String created_by;
	
	private String modified_by;
	
	private boolean isdelete;
 
	

	@PrePersist
	protected void onCreate() {
		created_date = LocalDateTime.now();
		modified_date = LocalDateTime.now();
		
	}
 


@PreUpdate
	protected void onUpdate() {
		modified_date = LocalDateTime.now();
	}


	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}


	
//	@ElementCollection
//    @CollectionTable(name = "destination_images", joinColumns = @JoinColumn(name = "destination_id"))
//    @Column(name = "image_url")
	//@NotEmpty(message="atlest one image url is required")
	@Column(name="dimage")
	//@ElementCollection
	private List< String> d_image;
	
	
	
 

	public List<String> getD_image() {
		return d_image;
	}

	public void setD_image(List<String> d_image) {
		this.d_image = d_image;
	}

	





	//	@ManyToOne(fetch = FetchType.LAZY)
	// @JsonIgnore
//	   	@JsonBackReference
	@JoinColumn(name = "c_id", nullable = false)
	    @ManyToOne(fetch = FetchType.EAGER)
	    private Country country;
	
//	   	@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
//	   	@JsonManagedReference
//	    private List< KeyAttraction >keyAttractions;
	
	@NotBlank(message="keyofattractions is reuired")
	private String keyofattractions;
 
	
public String getKeyofattractions() {
		return keyofattractions;
	}

	public void setKeyofattractions(String keyofattractions) {
		this.keyofattractions = keyofattractions;
	}

		//	 @ManyToOne(fetch = FetchType.LAZY, optional = false)  // Foreign key reference to State
	    @JoinColumn(name = "s_id", nullable = false)  // This will be the foreign key column in the Destination table
	    @ManyToOne(fetch = FetchType.EAGER)
	    private State state;
	    
//	    public List<ItineraryPkg> getItineraries() {
//			return itineraries;
//		}
//
//		public void setItineraries(List<ItineraryPkg> itineraries) {
//			this.itineraries = itineraries;
//		}
//
//		@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//		@JsonManagedReference
//	    private List<ItineraryPkg> itineraries;
//
//	
//		  @OneToMany(mappedBy = "destinationid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////			@JsonManagedReference
//		    private List<Booking> booking;
//	
// 
//	public List<Booking> getBooking() {
//			return booking;
//		}
//
//		public void setBooking(List<Booking> booking) {
//			this.booking = booking;
//		}

	public State getState() {
		return state;
	}
 
	public void setState(State state) {
		this.state = state;
	}
 
//	public List<KeyAttraction> getKeyAttractions() {
//	return keyAttractions;
//	}
// 
//	public void setKeyAttractions(List<KeyAttraction> keyAttractions) {
//		this.keyAttractions = keyAttractions;
//	}
 
	public Country getCountry() {
		return country;
	}
 
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
//   public void addKeyAttraction(String keyAttraction) {
//        String masterKey = "KA-" + UUID.randomUUID().toString();
//       this.keyAttraction.add(masterKey + ": " + keyAttraction);
//    }
 
	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

//	private String masterkey;
//	  @PrePersist
//	    public void generateMasterKey() {
//	        this.masterkey = "STATE-" + this.code+ "-" + UUID.randomUUID().toString();
//	    }
 
//	public List<String> getKeyAttraction() {
//		return keyAttraction;
//	}
 
//	public void setKeyAttraction(List<String> keyAttraction) {
//		this.keyAttraction = keyAttraction;
//	}
 
	
 
//	public Map<String, String> getKeyAttractionsWithMasterKey() {
//		return keyAttractionsWithMasterKey;
//	}
//
//	public void setKeyAttractionsWithMasterKey(Map<String, String> keyAttractionsWithMasterKey) {
//		this.keyAttractionsWithMasterKey = keyAttractionsWithMasterKey;
//	}
 
//	public String getMasterkey() {
//		return masterkey;
//	}
// 
//	public void setMasterkey(String masterkey) {
//		this.masterkey = masterkey;
//	}
 
	public static String getCode() {
		return code;
	}
 
	
 
	
	
	
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
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
 
	
 
	 public Destination(Long id) {
	        this.id = id;
	    }

	    // Default constructor (required by JPA)
	    public Destination() {
	    }
	
	
	
 
	
	
	
 
}
 
 

