package com.MotherSon.CRM.dto;

import java.util.List;

public class HotelPriceDTO {
 private Long hotel_price_id;
	private Long hotel_id;
	private String hotel_name;
	private String hotel_description;
	private String star_rating;
	private List< String> hotel_image;
	private String hotel_contact_person;
	private String hotel_contact_number;
	private String hotel_contact_email;
	private String hotel_address;
	private String hotel_pincode;
	private double off_season_price;
	private double extra_bed_price;
	private double direct_booking_price;
	private double third_party_price;
	private boolean status;	
	private Long country_id;
	private String country_name;
	private Long state_id;
	private String state_name;
	private Long destination_id;
	private String destination_name;
	private List< String> desitnation_image;
	private String destination_key_of_attraction;
	private Long room_id;
	private String room_name;
	private String room_bed_size;
	private int room_max_person;
	private  String room_image;
	private Long season_id;
	private String season_name;
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
	public String getHotel_description() {
		return hotel_description;
	}
	public void setHotel_description(String hotel_description) {
		this.hotel_description = hotel_description;
	}
	public String getStar_rating() {
		return star_rating;
	}
	public void setStar_rating(String star_rating) {
		this.star_rating = star_rating;
	}
	public List<String> getHotel_image() {
		return hotel_image;
	}
	public void setHotel_image(List<String> hotel_image) {
		this.hotel_image = hotel_image;
	}
	public String getHotel_contact_person() {
		return hotel_contact_person;
	}
	public void setHotel_contact_person(String hotel_contact_person) {
		this.hotel_contact_person = hotel_contact_person;
	}
	public String getHotel_contact_number() {
		return hotel_contact_number;
	}
	public void setHotel_contact_number(String hotel_contact_number) {
		this.hotel_contact_number = hotel_contact_number;
	}
	public String getHotel_contact_email() {
		return hotel_contact_email;
	}
	public void setHotel_contact_email(String hotel_contact_email) {
		this.hotel_contact_email = hotel_contact_email;
	}
	public String getHotel_address() {
		return hotel_address;
	}
	public void setHotel_address(String hotel_address) {
		this.hotel_address = hotel_address;
	}
	public String getHotel_pincode() {
		return hotel_pincode;
	}
	public void setHotel_pincode(String hotel_pincode) {
		this.hotel_pincode = hotel_pincode;
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
	public Long getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public Long getState_id() {
		return state_id;
	}
	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Long getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(Long destination_id) {
		this.destination_id = destination_id;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	public List<String> getDesitnation_image() {
		return desitnation_image;
	}
	public void setDesitnation_image(List<String> desitnation_image) {
		this.desitnation_image = desitnation_image;
	}
	public String getDestination_key_of_attraction() {
		return destination_key_of_attraction;
	}
	public void setDestination_key_of_attraction(String destination_key_of_attraction) {
		this.destination_key_of_attraction = destination_key_of_attraction;
	}
	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_bed_size() {
		return room_bed_size;
	}
	public void setRoom_bed_size(String room_bed_size) {
		this.room_bed_size = room_bed_size;
	}
	public int getRoom_max_person() {
		return room_max_person;
	}
	public void setRoom_max_person(int room_max_person) {
		this.room_max_person = room_max_person;
	}
	
	public String getRoom_image() {
		return room_image;
	}
	public void setRoom_image(String room_image) {
		this.room_image = room_image;
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
	public HotelPriceDTO(Long hotel_price_id, Long hotel_id, String hotel_name, String hotel_description,
			String star_rating, List<String> hotel_image, String hotel_contact_person, String hotel_contact_number,
			String hotel_contact_email, String hotel_address, String hotel_pincode, double off_season_price,
			double extra_bed_price, double direct_booking_price, double third_party_price, boolean status,
			Long country_id, String country_name, Long state_id, String state_name, Long destination_id,
			String destination_name, List<String> desitnation_image, String destination_key_of_attraction, Long room_id,
			String room_name, String room_bed_size, int room_max_person, String string, Long season_id,
			String season_name) {
		super();
		this.hotel_price_id = hotel_price_id;
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.hotel_description = hotel_description;
		this.star_rating = star_rating;
		this.hotel_image = hotel_image;
		this.hotel_contact_person = hotel_contact_person;
		this.hotel_contact_number = hotel_contact_number;
		this.hotel_contact_email = hotel_contact_email;
		this.hotel_address = hotel_address;
		this.hotel_pincode = hotel_pincode;
		this.off_season_price = off_season_price;
		this.extra_bed_price = extra_bed_price;
		this.direct_booking_price = direct_booking_price;
		this.third_party_price = third_party_price;
		this.status = status;
		this.country_id = country_id;
		this.country_name = country_name;
		this.state_id = state_id;
		this.state_name = state_name;
		this.destination_id = destination_id;
		this.destination_name = destination_name;
		this.desitnation_image = desitnation_image;
		this.destination_key_of_attraction = destination_key_of_attraction;
		this.room_id = room_id;
		this.room_name = room_name;
		this.room_bed_size = room_bed_size;
		this.room_max_person = room_max_person;
		this.room_image = string;
		this.season_id = season_id;
		this.season_name = season_name;
	}
	
	
}
