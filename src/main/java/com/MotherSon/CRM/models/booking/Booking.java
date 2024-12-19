package com.MotherSon.CRM.models.booking;
 
import java.time.LocalDateTime;
import java.util.List;
 
import com.MotherSon.CRM.models.Customer;
import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
 
 
@Entity
@Table(name = "booking_master")
public class Booking {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
 
 
	private String bookingStatus;
	
	
 
 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	//@JsonBackReference
	private User bookingByuserId;
	//private Long bookingByUserId;
	
	//private String bookingByUserName;
	
	private String to_destination;
	
	
 
 
	
 
 
	public String getTo_destination() {
		return to_destination;
	}
 
 
	public void setTo_destination(String to_destination) {
		this.to_destination = to_destination;
	}
 
 
	@Column(name="From_Destination",nullable=false)
	private String from_Destination;
	
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="destination_id")
	//@JsonBackReference
	private Destination destination;
	
	
	
	private boolean isdelete;
	
	private String ipaddress;
	
	@Column(name = "created_Date")
	private LocalDateTime createdDate;
	
	@Column(name = "modified_Date")
	private LocalDateTime modifiedDate;
	
	
	@Column(name = "modified_By")
	private String modifiedBy;
	
	
	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
		modifiedDate = LocalDateTime.now();
	}
	
	
   @PreUpdate
    protected void onUpdate() {
	   modifiedDate = LocalDateTime.now();
   }
 
 
 
	public String getBookingStatus() {
		return bookingStatus;
	}
 
 
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
 
 
	@Column(name="customer_id_proof", nullable=false)
	private List<String> customerIdProof;
	
	
 
	
	
	
	public String getFrom_Destination() {
		return from_Destination;
	}
 
 
	public void setFrom_Destination(String from_Destination) {
		this.from_Destination = from_Destination;
	}
 
 
 
	public List<String> getCustomerIdProof() {
		return customerIdProof;
	}
 
 
 
 
 
	public void setCustomerIdProof(List<String> customerIdProof) {
		this.customerIdProof = customerIdProof;
	}
 
 
	
 
 
	@Embedded
	private TravelDetails travelDetails;
	
 
 
	@Embedded
	private PaymentDetails paymentDetails;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = " cust_id")
	private Customer customer;
 
	
   private Boolean status;
 
 
	
 
	public Boolean getStatus() {
	return status;
}
 
 
public void setStatus(Boolean status) {
	this.status = status;
}
 
 
	public Customer getCustomer() {
		return customer;
	}
 
 
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
 
 
 
	public Long getId() {
		return Id;
	}
 
 
	
 
	public Destination getDestination() {
		return destination;
	}
 
 
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
 
 
	public void setId(Long id) {
		Id = id;
	}
 
 
 
	public TravelDetails getTravelDetails() {
		return travelDetails;
	}
 
 
	public void setTravelDetails(TravelDetails travelDetails) {
		this.travelDetails = travelDetails;
	}
 
 
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
 
 
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
 
 
	public User getBookingByuserId() {
		return bookingByuserId;
	}
 
 
	public void setBookingByuserId(User bookingByuserId) {
		this.bookingByuserId = bookingByuserId;
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
 
 
	public String getModifiedBy() {
		return modifiedBy;
	}
 
 
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	} 
}	
