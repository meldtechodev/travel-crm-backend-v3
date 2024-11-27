package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.repository.RoomTypesRepository;

@Service
	public class RoomTypesService {
		
		@Autowired
		private RoomTypesRepository roomtypesRepository;
		
		
		public List<RoomTypes> getAllRoomTypes() {
			return roomtypesRepository.findAll();
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

