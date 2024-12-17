package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		
		
		}

