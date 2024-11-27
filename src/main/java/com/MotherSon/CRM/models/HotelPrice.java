package com.MotherSon.CRM.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
 @Table(name="hotelprice_master")

public class HotelPrice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "hotel_id")
	// @JsonBackReference
	
	private Hotel hotel;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="roomTypes_id")
	//@JsonBackReference
	
   private RoomTypes roomtypes;
//    @ManyToOne
//    @JoinColumn(name = "room_types_id")
//    //@JsonManagedReference
//    /private RoomTypes roomtypes;

    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomTypes getRoomtypes() {
		return roomtypes;
	}

	public void setRoomtypes(RoomTypes roomtypes) {
		this.roomtypes = roomtypes;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id")
   // @JsonBackReference 

   private Season season;

	private double off_season_price;
	
	private double extra_bed_price;
	
	private double direct_booking_price;
	
	private double third_party_price;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
//	public Hotel getHotel() {
//		return hotel;
//	}
////
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public double getOff_season_price() {
		return off_season_price;
	}

	public void setOff_season_price(double off_season_price) {
		this.off_season_price = off_season_price;
	}

	public double getExtra_bed_price() {
		return extra_bed_price;
	}

	public void setExtra_bed_price(double extra_bed_price) {
		this.extra_bed_price = extra_bed_price;
	}

	public double getDirect_booking_price() {
		return direct_booking_price;
	}

	public void setDirect_booking_price(double direct_booking_price) {
		this.direct_booking_price = direct_booking_price;
	}

	public double getThird_party_price() {
		return third_party_price;
	}

	public void setThird_party_price(double third_party_price) {
		this.third_party_price = third_party_price;
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
	}


