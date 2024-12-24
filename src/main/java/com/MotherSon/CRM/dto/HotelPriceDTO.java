package com.MotherSon.CRM.dto;

import java.util.List;

public class HotelPriceDTO {
    private Long hotel_price_id;
	private Long hotel_id;
	private String hotel_name;
	private Long room_type_id;
	private String rooms_name;
	private Long season_id;
	private String season_name;
	private double off_season_price;
	private double extra_bed_price;
	private double direct_booking_price;
	private double third_party_price;
	
	public HotelPriceDTO(Long hotel_price_id, Long hotel_id, String hotel_name, Long room_type_id, String rooms_name,
			Long season_id, String season_name, double off_season_price, double extra_bed_price,
			double direct_booking_price, double third_party_price) {
		super();
		this.hotel_price_id = hotel_price_id;
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.room_type_id = room_type_id;
		this.rooms_name = rooms_name;
		this.season_id = season_id;
		this.season_name = season_name;
		this.off_season_price = off_season_price;
		this.extra_bed_price = extra_bed_price;
		this.direct_booking_price = direct_booking_price;
		this.third_party_price = third_party_price;
	}
	public Long getHotel_price_id() {
		return hotel_price_id;
	}
	public void setHotel_price_id(Long hotel_price_id) {
		this.hotel_price_id = hotel_price_id;
	}
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public Long getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Long room_type_id) {
		this.room_type_id = room_type_id;
	}
	public String getRooms_name() {
		return rooms_name;
	}
	public void setRooms_name(String rooms_name) {
		this.rooms_name = rooms_name;
	}
	public Long getSeason_id() {
		return season_id;
	}
	public void setSeason_id(Long season_id) {
		this.season_id = season_id;
	}
	public String getSeason_name() {
		return season_name;
	}
	public void setSeason_name(String season_name) {
		this.season_name = season_name;
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
	
}