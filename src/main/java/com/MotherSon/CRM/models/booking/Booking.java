package com.MotherSon.CRM.models.booking;

import java.util.List;

import com.MotherSon.CRM.models.Destination;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "booking_mater")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	


	private String bookingStatus;
	
	private Long bookingByUserId;
	
	private String bookingByUserName;
	
	private String to_destination;
	
	


	


	public String getTo_destination() {
		return to_destination;
	}


	public void setTo_destination(String to_destination) {
		this.to_destination = to_destination;
	}


	@Column(name="From_Destination",nullable=false)
	private String from_Destination;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="destination_id", nullable=false)
	//@JsonBackReference
	private Destination destination;
	
	

	public Long getBookingByUserId() {
		return bookingByUserId;
	}


	public void setBookingByUserId(Long bookingByUserId) {
		this.bookingByUserId = bookingByUserId;
	}


	public String getBookingByUserName() {
		return bookingByUserName;
	}


	


	public void setBookingByUserName(String bookingByUserName) {
		this.bookingByUserName = bookingByUserName;
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


//	public Destination getTo_Destination() {
//		return to_Destination;
//	}
//
//
//	public void setTo_Destination(Destination to_Destination) {
//		this.to_Destination = to_Destination;
//	}


	public List<String> getCustomerIdProof() {
		return customerIdProof;
	}


//	public Destination getDestination() {
//		return Destination;
//	}
//
//
//	public void setDestination(Destination destination) {
//		Destination = destination;
//	}


	public void setCustomerIdProof(List<String> customerIdProof) {
		this.customerIdProof = customerIdProof;
	}


	


	@Embedded
	private TravelDetails travelDetails;
	


	@Embedded
	private PaymentDetails paymentDetails;
	
	
	private Long cust_Id;

	
  


	

	public Destination getDestination() {
		return destination;
	}


	public void setDestination(Destination destination) {
		this.destination = destination;
	}


	public Long getCust_Id() {
		return cust_Id;
	}


	public void setCust_Id(Long cust_Id) {
		this.cust_Id = cust_Id;
	}


	public Long getId() {
		return Id;
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


	

}	