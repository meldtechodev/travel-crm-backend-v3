package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.HotelPriceDTO;
import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.repository.HotelPriceRepository;

@Service
	public class HotelPriceService {
		
		
		@Autowired
	    private HotelPriceRepository hotelpriceRepository;
		


		public HotelPrice addHotelPrice(HotelPrice hotelprice) {
			return hotelpriceRepository.save(hotelprice);
		}
		
		public List<HotelPrice> getAllHotelPrice(){
			return hotelpriceRepository.findAll();
		}
		
		public Optional<HotelPrice> getHotelPriceById1(Long id){
			return hotelpriceRepository.findById(id);
		}
		
		public Optional<HotelPriceDTO> getHotelPriceById(Long id) {
		    // Fetch the hotel price entity by ID
		    Optional<HotelPrice> hotelPriceEntity = hotelpriceRepository.findById(id);

		    // Map the entity to DTO if present
		    return hotelPriceEntity.map(entity -> new HotelPriceDTO(
		        entity.getId(),
		        entity.getHotel().getId(),
		        entity.getHotel().getHname(),
		        entity.getHotel().getHdescription(),
		        entity.getHotel().getStar_ratings(),
		        entity.getHotel().getHimage(),
		        entity.getHotel().getHcontactname(),
		        entity.getHotel().getHcontactnumber(),
		        entity.getHotel().getHcontactemail(),
		        entity.getHotel().getHaddress(),
		        entity.getHotel().getHpincode(),
		        entity.getOff_season_price(),
		        entity.getExtra_bed_price(),
		        entity.getDirect_booking_price(),
		        entity.getThird_party_price(),
		        entity.isStatus(),
		        entity.getHotel().getCountry().getId(),
		        entity.getHotel().getCountry().getCountryName(),
		        entity.getHotel().getState().getId(),
		        entity.getHotel().getState().getStateName(),
		        entity.getHotel().getDestination().getId(),
		        entity.getHotel().getDestination().getDestinationName(),
		        entity.getHotel().getDestination().getD_image(),
		        entity.getHotel().getDestination().getKeyofattractions(),
		        entity.getRoomtypes().getRooms().getId(),
		        entity.getRoomtypes().getRooms().getRoomname(),
		        entity.getRoomtypes().getBedSize(),
		        entity.getRoomtypes().getMax_person(),
		        entity.getRoomtypes().getRimage(),
		        entity.getSeason().getId(),
		        entity.getSeason().getSeasonName()
		    ));
		}
		
		public void deleteById(Long id) {
			 hotelpriceRepository.deleteById(id);
		}
		
		public HotelPrice findById(Long id) {
			return null;
		}
		
		public Optional<HotelPriceDTO> getHotelPriceByHotelId(Long hotelId) {
		    // Fetch the first hotel price entity associated with the given hotel ID
		    Optional<HotelPrice> hotelPriceEntity = hotelpriceRepository.findByHotel_Id(hotelId);
 
		    // Map the entity to DTO if present
		    return hotelPriceEntity.map(entity -> new HotelPriceDTO(
		        entity.getId(),
		        entity.getHotel().getId(),
		        entity.getHotel().getHname(),
		        entity.getHotel().getHdescription(),
		        entity.getHotel().getStar_ratings(),
		        entity.getHotel().getHimage(),
		        entity.getHotel().getHcontactname(),
		        entity.getHotel().getHcontactnumber(),
		        entity.getHotel().getHcontactemail(),
		        entity.getHotel().getHaddress(),
		        entity.getHotel().getHpincode(),
		        entity.getOff_season_price(),
		        entity.getExtra_bed_price(),
		        entity.getDirect_booking_price(),
		        entity.getThird_party_price(),
		        entity.isStatus(),
		        entity.getHotel().getCountry().getId(),
		        entity.getHotel().getCountry().getCountryName(),
		        entity.getHotel().getState().getId(),
		        entity.getHotel().getState().getStateName(),
		        entity.getHotel().getDestination().getId(),
		        entity.getHotel().getDestination().getDestinationName(),
		        entity.getHotel().getDestination().getD_image(),
		        entity.getHotel().getDestination().getKeyofattractions(),
		        entity.getRoomtypes().getRooms().getId(),
		        entity.getRoomtypes().getRooms().getRoomname(),
		        entity.getRoomtypes().getBedSize(),
		        entity.getRoomtypes().getMax_person(),
		        entity.getRoomtypes().getRimage(),
		        entity.getSeason().getId(),
		        entity.getSeason().getSeasonName()
		    ));
		}
 
		
		

	}

