package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.repository.RoomTypesRepository;

@Service
	public class RoomTypesService {
		
		@Autowired
		private RoomTypesRepository roomtypesRepository;
		
		
//		public List<RoomTypes> getAllRoomTypes() {
//			return roomtypesRepository.findAll();
//		}
		
public Object getRoomTypes(Long hotelId, Long roomTypeId) {
			
			
			if (roomTypeId != null) {
		        Optional<RoomTypes> roomType = roomtypesRepository.findById(roomTypeId);
		        if (roomType.isPresent()) {
		            return roomType.get(); // Return the room type
		        } else {
		            return "Room type not found with id: " + roomTypeId;
		        }
		    }
			
			else if(hotelId != null) {
				List<RoomTypes> roomtype = roomtypesRepository.findByHotelId(hotelId);
				if(roomtype.isEmpty()) {
					return "Record Not Found";
				}
				return roomtype;
			}
			else
			{
				return roomtypesRepository.findAll();
			}
		}
 
		
		public Page<RoomTypes> getRoomTypes(int page , int size , String sortDirection){
			Sort sort = Sort.by(Sort.Order.asc("bedSize"));
			
			if("desc".equalsIgnoreCase(sortDirection)) {
				sort =  Sort.by(Sort.Order.desc("bedSize"));
			}
			
			PageRequest  pageable = PageRequest.of(page, size, sort);
			return roomtypesRepository.findAll(pageable);
			}
		
		
		public RoomTypes addRoomTypes(RoomTypes roomtypes) {
			return roomtypesRepository.save(roomtypes);
		}
	//	
	//	
		public RoomTypes getRoomTypesById(Long id) {
	        return roomtypesRepository.findById(id)
	       		.orElseThrow(() -> new RuntimeException("RoomTypes not found"));
	    
		}
	    public RoomTypes updateRoomTypesById(Long id, RoomTypes roomtypes) {
	        roomtypes.setId(id); // Ensure the ID is set
	       return roomtypesRepository.save(roomtypes); // Save the updated booking
	   }
	    
		
	    
	public Optional<RoomTypes> getRoomTypessById(Long id) {
			return roomtypesRepository.findById(id);
		}



	public RoomTypes findById(long id) {
		return null ;
		
	}
	public void deleteById(long id) {
		roomtypesRepository.deleteById(id);
		
	}

	}

