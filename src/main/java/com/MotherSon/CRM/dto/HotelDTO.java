package com.MotherSon.CRM.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HotelDTO {

    // Existing fields
    private String hname;
    private String hdescription;
    private String star_ratings;
    private List<String> himage;
    private String hcontactname;
    private String hcontactnumber;
    private String hcontactemail;
    private String haddress;
    private String hpincode;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String created_by;
    private String modified_by;
    private String ipaddress;
    private boolean status;
    private boolean isdelete;
    private Long destinationId;
    private String destinationName;
    private Long stateId;
    private String stateName;
    private Long countryId;
    private String countryName;
    private Long hotel_id;

    // New field for room type details
    private List<RoomTypesDTO> roomTypes;
    private List<HotelPriceDTO> hotelPrice;
    
    
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


	public Long getDestinationId() {
		return destinationId;
	}


	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}


	public String getDestinationName() {
		return destinationName;
	}


	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}


	public Long getStateId() {
		return stateId;
	}


	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public Long getCountryId() {
		return countryId;
	}


	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public Long getHotel_id() {
		return hotel_id;
	}


	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}


	public List<RoomTypesDTO> getRoomTypes() {
		return roomTypes;
	}


	public void setRoomTypes(List<RoomTypesDTO> roomTypes) {
		this.roomTypes = roomTypes;
	}


	public List<HotelPriceDTO> getHotelPrice() {
		return hotelPrice;
	}


	public void setHotelPrice(List<HotelPriceDTO> hotelPrice) {
		this.hotelPrice = hotelPrice;
	}


	public HotelDTO(String hname, String hdescription, String star_ratings, List<String> himage, String hcontactname,
			String hcontactnumber, String hcontactemail, String haddress, String hpincode, LocalDateTime created_date,
			LocalDateTime modified_date, String created_by, String modified_by, String ipaddress, boolean status,
			boolean isdelete, Long destinationId, String destinationName, Long stateId, String stateName,
			Long countryId, String countryName, Long hotel_id, List<RoomTypesDTO> roomTypes,
			List<HotelPriceDTO> hotelPrice) {
		super();
		this.hname = hname;
		this.hdescription = hdescription;
		this.star_ratings = star_ratings;
		this.himage = himage;
		this.hcontactname = hcontactname;
		this.hcontactnumber = hcontactnumber;
		this.hcontactemail = hcontactemail;
		this.haddress = haddress;
		this.hpincode = hpincode;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.ipaddress = ipaddress;
		this.status = status;
		this.isdelete = isdelete;
		this.destinationId = destinationId;
		this.destinationName = destinationName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.countryId = countryId;
		this.countryName = countryName;
		this.hotel_id = hotel_id;
		this.roomTypes = roomTypes;
		this.hotelPrice = hotelPrice;
	}
    
	

}