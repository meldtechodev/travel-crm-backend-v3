package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.HotelDTO;
import com.MotherSon.CRM.dto.HotelPriceDTO;
import com.MotherSon.CRM.dto.Response;
import com.MotherSon.CRM.dto.RoomTypesDTO;
import com.MotherSon.CRM.models.Hotel;
import com.MotherSon.CRM.repository.HotelServiceImpl;

@Service
	public class HotelService {
		
		@Autowired
		private HotelServiceImpl hotelServiceImpl;
		
		
		
		public Hotel addHotel(Hotel hotel) {
			return hotelServiceImpl.save(hotel);
		}
		
		public boolean existsByhname(String hname) {
	        return hotelServiceImpl.existsByhname(hname);
	    }
		
//		public Hotel updateHotelById(Long id, Hotel hotelDetails) {
//	        Hotel existingHotel = hotelServiceImpl.findById(id)
//	                .orElseThrow(() -> new RuntimeException("Hotl not found"));
//
//	        existingHotel.setHname(hotelDetails.getHname());
//	        existingHotel.setHdescription(hotelDetails.getHdescription());
//	        existingHotel.setStar_ratings(hotelDetails.getStar_ratings());
//	        existingHotel.setHimage(hotelDetails.getHimage());
//	        existingHotel.setHcontactname(hotelDetails.getHcontactname());
//	        existingHotel.setHcontactnumber(hotelDetails.getHcontactnumber());
//	        existingHotel.setHcontactemail(hotelDetails.getHcontactemail());
//	        existingHotel.setHaddress(hotelDetails.getHaddress());
//	        existingHotel.setHpincode(hotelDetails.getHpincode());
//	        existingHotel.setCreated_date(hotelDetails.getCreated_date());
//	        existingHotel.setModified_date(hotelDetails.getModified_date());
//	        existingHotel.setCreated_by(hotelDetails.getCreated_by());
//	        existingHotel.setModified_by(hotelDetails.getModified_by());
//	        existingHotel.setIpaddress(hotelDetails.getIpaddress());
//	        existingHotel.setStatus(hotelDetails.isStatus());
//	        existingHotel.setIsdelete(hotelDetails.isIsdelete());
//	        
//	       
//	        
//	        return hotelServiceImpl.save(existingHotel);
//	    }


		public Hotel updateHotelById(Long id, Hotel hotelDetails) {
	        Hotel existingHotel = hotelServiceImpl.findById(id)
	                .orElseThrow(() -> new RuntimeException("Hotl not found"));
 
	        existingHotel.setHname(hotelDetails.getHname());
	        existingHotel.setHdescription(hotelDetails.getHdescription());
	        existingHotel.setStar_ratings(hotelDetails.getStar_ratings());
	        existingHotel.setHimage(hotelDetails.getHimage());
	        existingHotel.setHcontactname(hotelDetails.getHcontactname());
	        existingHotel.setHcontactnumber(hotelDetails.getHcontactnumber());
	        existingHotel.setHcontactemail(hotelDetails.getHcontactemail());
	        existingHotel.setHaddress(hotelDetails.getHaddress());
	        existingHotel.setHpincode(hotelDetails.getHpincode());
	        existingHotel.setCreated_date(hotelDetails.getCreated_date());
	        existingHotel.setModified_date(hotelDetails.getModified_date());
	        existingHotel.setCreated_by(hotelDetails.getCreated_by());
	        existingHotel.setModified_by(hotelDetails.getModified_by());
	        existingHotel.setIpaddress(hotelDetails.getIpaddress());
	        existingHotel.setStatus(hotelDetails.isStatus());
	        existingHotel.setIsdelete(hotelDetails.isIsdelete());
	        
	       
	        
	        return hotelServiceImpl.save(existingHotel);
	    }
  
		public Hotel getHotelById(Long id) {
	        return hotelServiceImpl.findById(id)
	                .orElseThrow(() -> new RuntimeException("Destination not found"));
	    }
		
		

//		public Optional<Hotel> updateHotel(long id, Hotel hotelDetails) {
//	        return hotelServiceImpl.findById(id).map(hotel -> {
//	            hotel.setHimage(hotelDetails.getHimage());
//	            hotel.setRoomtype(hotelDetails.getRoomtype());
//	            hotel.setRoomsize(hotelDetails.getRoomsize());
//	            hotel.setBedtype(hotelDetails.getBedtype());
//	            hotel.setBedsize(hotelDetails.getBedsize());
//	            hotel.setHcontactname(hotelDetails.getHcontactname());
//	            hotel.setHcontactnumber(hotelDetails.getHcontactnumber());
//	            hotel.setHcontactemail(hotelDetails.getHcontactemail());
//	            hotel.setHamenities(hotelDetails.getHamenities());
//	            hotel.setHaddress(hotelDetails.getHaddress());
//	            hotel.setHpincode(hotelDetails.getHpincode());
//	            hotel.setHrating(hotelDetails.getHrating());
//	            hotel.setHstatus(hotelDetails.getHstatus());
//	            return hotelServiceImpl.save(hotel);
//	        });
//	    }
	//
//	    public Optional<Hotel> getHotelById(long id) {
//	        return hotelServiceImpl.findById(id);
//	    }
			
		
		public Hotel findById(long id) {
			return null;
			
		}
		

		public void deleteById(long id) {
			hotelServiceImpl.deleteById(id);
			
		}

		public List<Hotel> getAllHotel() {
			return hotelServiceImpl.findAll();
		}

		
//		public List<Hotel> getHotelByDestinationIdAnd(Long destinationid) {
//			return hotelServiceImpl.findByDestinationIdAnd(destinationid);
//		}
		public Optional<Hotel> getHotelsById(Long id) {
	        return hotelServiceImpl.findById(id);
	    }

				public Response<Object> getHotel(Long stateId, Long countryId, Long destinationId, Long hotelId) {
		    if (hotelId != null) {
		        Optional<Hotel> hotelOptional = hotelServiceImpl.findById(hotelId);
		        if (hotelOptional.isPresent()) {
		            HotelDTO hotelDTO = convertToHotelDTO(hotelOptional.get());
		            return new Response<>("Successful", "Getting hotel successfully", hotelDTO);
		        } else {
		            return new Response<>("Failure", "No hotel found with hotelId: " + hotelId, null);
		        }
		    } else {
		        List<Hotel> hotels = hotelServiceImpl.findAll().stream()
		            .filter(hotel -> !hotel.isIsdelete()) // Exclude deleted hotels
		            .collect(Collectors.toList());

		        if (hotels.isEmpty()) {
		            return new Response<>("Failure", "No hotels found", null);
		        }

		        List<HotelDTO> hotelDTOList = hotels.stream().map(this::convertToHotelDTO).collect(Collectors.toList());
		        return new Response<>("Successful", "Getting all hotels successfully", hotelDTOList);
		    }
		}

		private HotelDTO convertToHotelDTO(Hotel hotel) {
		    // Map RoomTypes to RoomTypesDTO
		    List<RoomTypesDTO> roomTypeDTOs = hotel.getRoomtypes().stream()
		        .map(roomType -> new RoomTypesDTO(
		            roomType.getId(),
		            roomType.getRooms() != null ? roomType.getRooms().getRoomname() : null,
		            roomType.getBedSize()
		        ))
		        .collect(Collectors.toList());

		    // Map HotelPrices to HotelPriceDTO
		    List<HotelPriceDTO> hotelPriceDTOs = hotel.getHotelprice().stream()
		        .map(hotelPrice -> new HotelPriceDTO(
		            hotelPrice.getId(),
		            hotel.getId(),
		            hotel.getHname(),
		            hotelPrice.getRoomtypes() != null ? hotelPrice.getRoomtypes().getId() : null,
		            hotelPrice.getRoomtypes() != null ? hotelPrice.getRoomtypes().getRooms().getRoomname() : null,
		            hotelPrice.getSeason() != null ? hotelPrice.getSeason().getId() : null,
		            hotelPrice.getSeason() != null ? hotelPrice.getSeason().getSeasonName() : null,
		            hotelPrice.getOff_season_price(),
		            hotelPrice.getExtra_bed_price(),
		            hotelPrice.getDirect_booking_price(),
		            hotelPrice.getThird_party_price()
		        ))
		        .collect(Collectors.toList());

		    // Return HotelDTO with room types and hotel prices included
		    return new HotelDTO(
		        hotel.getHname(),
		        hotel.getHdescription(),
		        hotel.getStar_ratings(),
		        hotel.getHimage(),
		        hotel.getHcontactname(),
		        hotel.getHcontactnumber(),
		        hotel.getHcontactemail(),
		        hotel.getHaddress(),
		        hotel.getHpincode(),
		        hotel.getCreated_date(),
		        hotel.getModified_date(),
		        hotel.getCreated_by(),
		        hotel.getModified_by(),
		        hotel.getIpaddress(),
		        hotel.isStatus(),
		        hotel.isIsdelete(),
		        hotel.getDestination() != null ? hotel.getDestination().getId() : null,
		        hotel.getDestination() != null ? hotel.getDestination().getDestinationName() : null,
		        hotel.getState() != null ? hotel.getState().getId() : null,
		        hotel.getState() != null ? hotel.getState().getStateName() : null,
		        hotel.getCountry() != null ? hotel.getCountry().getId() : null,
		        hotel.getCountry() != null ? hotel.getCountry().getCountryName() : null,
		        hotel.getId(),
		        roomTypeDTOs,
		        hotelPriceDTOs
		    );
		}
}

