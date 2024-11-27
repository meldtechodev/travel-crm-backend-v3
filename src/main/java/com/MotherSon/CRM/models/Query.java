package com.MotherSon.CRM.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)

@Table(name = "query_master")
public class Query {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	
//	@NotBlank(message = "proposal Id is required")
//	@Column(name = "proposal_id" , nullable = false)
	private String proposalId;
	
	@NotBlank(message = "requirementType is required")
	@Column(name = "requirement_type" , nullable = false)
	private String requirementType;
	
	
	//@NotBlank(message = " TravelDate is required")
	 @Column(name = "travel_Date", nullable = false)
	  private LocalDateTime travelDate;
	
	
	//@Min(value = 1, message = "Days must be at least 1")
	// @Column(name = "days", nullable = true)
	 private Integer days;
	
	// @Min(value = 1, message = "Nights must be at least 1")
	//@Column(name = "nights", nullable = true)
	 private Integer nights;
	
	 
	 @Min(value = 1, message = "Total Travellers must be at least 1")
	 @Max(value = 500, message = "Total Travellers cannot exceed 500")
	 @Column(name = "total_Travellers", nullable = true)
	 private Integer totalTravellers;
	
	 
	 @Min(value = 0, message = "Adults cannot be negative")
	 @Column(name = "adults", nullable = true)
	 private Integer adults;
	
	 @Min(value = 0, message = "Kids cannot be negative")
	 @Column(name = "kids", nullable = true)
	 private Integer kids;
	
	 @Min(value = 0, message = "Infants cannot be negative")
	 @Column(name = "infants", nullable = true)
	 private Integer infants;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "pkg_id")
	//@JsonIgnore
	//@JsonBackReference
	private Pkg pkg;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Destination did;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Destination fromcityid;
	
	public Destination getDid() {
		return did;
	}

	public void setDid(Destination did) {
		this.did = did;
	}

	public Destination getFromcityid() {
		return fromcityid;
	}

	public void setFromcityid(Destination fromcityid) {
		this.fromcityid = fromcityid;
	}

	@NotBlank(message = "Salutation is required")
	@Column(name = "salutation" , nullable = false)
	private String salutation;
	
	@NotBlank(message = "Fname name is required")
	@Column(name = "Fname" , nullable = false)
	private String fname;
	
	@NotBlank(message = "Last name is required")
	@Column(name = "Lname" , nullable = false)
	private String lname;
	
	@NotBlank(message = "Email id is required")
	@Email(message = "Email ID must be a valid email address")
	@Column(name = "Email_id" , nullable = false)
	private String emailId;
	
	@NotBlank(message = "Contact No is required")
	@Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Contact No must be a valid phone number")
	@Column(name = "contact_No" , nullable = false)
	private String contactNo;
	
	@NotBlank(message = "leadSource is required")
	@Column(name = "lead_Source", nullable = false)
	private String leadSource;
	
	private String foodPreferences;
	
	@PositiveOrZero(message = "Basic Cost cannot be negative")
	private double basicCost;
	
	@PositiveOrZero(message = "GST cannot be negative")
	private double gst;
	
	@PositiveOrZero(message = "Total Cost cannot be negative")
	private double totalCost;
	
	//@PastOrPresent(message = "Query Date cannot be in the future")
	private LocalDateTime query_Date;
	
	private String queryType;
	
	private String queryCreatedFrom;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userId;
	
	private boolean emailStatus;
	
	private String leadStatus;
		
	private LocalDateTime lastUpdated_Date;
	
private String ipAddress;
	
 
	
	public String getIpAddress() {
		return ipAddress;
	}
 
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
 
	
	
	@PrePersist
	protected void onCreate() {
		
		
		 if (proposalId == null || proposalId.isEmpty()) {
		        proposalId = "PROPOSAL-" + UUID.randomUUID().toString();  // Or any other custom logic
		    }
		 
		query_Date = LocalDateTime.now();
		lastUpdated_Date = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		lastUpdated_Date = LocalDateTime.now();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	public String getRequirementType() {
		return requirementType;
	}

	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}

	public LocalDateTime getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDateTime travelDate) {
		this.travelDate = travelDate;
	}

	

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public Integer getTotalTravellers() {
		return totalTravellers;
	}

	public void setTotalTravellers(Integer totalTravellers) {
		this.totalTravellers = totalTravellers;
	}

	public Integer getAdults() {
		return adults;
	}

	public void setAdults(Integer adults) {
		this.adults = adults;
	}

	public Integer getKids() {
		return kids;
	}

	public void setKids(Integer kids) {
		this.kids = kids;
	}

	public Integer getInfants() {
		return infants;
	}

	public void setInfants(Integer infants) {
		this.infants = infants;
	}

	public Pkg getPkg() {
		return pkg;
	}

	public void setPkg(Pkg pkg) {
		this.pkg = pkg;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getFoodPreferences() {
		return foodPreferences;
	}

	public void setFoodPreferences(String foodPreferences) {
		this.foodPreferences = foodPreferences;
	}

	public double getBasicCost() {
		return basicCost;
	}

	public void setBasicCost(double basicCost) {
		this.basicCost = basicCost;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getQuery_Date() {
		return query_Date;
	}

	public void setQuery_Date(LocalDateTime query_Date) {
		this.query_Date = query_Date;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryCreatedFrom() {
		return queryCreatedFrom;
	}

	public void setQueryCreatedFrom(String queryCreatedFrom) {
		this.queryCreatedFrom = queryCreatedFrom;
	}

//	public Long getQueryAssigned() {
//		return queryAssigned;
//	}
//
//	public void setQueryAssigned(Long queryAssigned) {
//		this.queryAssigned = queryAssigned;
//	}
	
	
	
	

	public boolean isEmailStatus() {
		return emailStatus;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public void setEmailStatus(boolean emailStatus) {
		this.emailStatus = emailStatus;
	}

	

	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public LocalDateTime getLastUpdated_Date() {
		return lastUpdated_Date;
	}

	public void setLastUpdated_Date(LocalDateTime lastUpdated_Date) {
		this.lastUpdated_Date = lastUpdated_Date;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	

	
	
	
	
}