package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import com.MotherSon.CRM.models.Role;
//import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.repository.RoomsRepository;

@Service
	public class RoomsService {
		
		
		@Autowired
		private RoomsRepository roomsRepository;
		
		
//		public Rooms addRooms(Rooms rooms) {
//			return roomsRepository.save(rooms);
//		}
		
		
		
		public Rooms addRooms(Rooms rooms) {
			if(roomsRepository.existsByRoomname(rooms.getRoomname())) {
			throw new IllegalArgumentException(" Season Name With This Name " + rooms.getRoomname() + " Already exists ");
		}
		return roomsRepository.save(rooms);
		}
		
//		public List<Rooms> getAllRoom() {
//			return roomsRepository.findAll();
//		}
		
		
		
		public Page<Rooms> getRooms(int page , int size , String sortDirection){
			Sort sort = Sort.by(Sort.Order.asc("roomname"));
			
			if("desc".equalsIgnoreCase(sortDirection)) {
				sort =  Sort.by(Sort.Order.desc("roomname"));
			}
			
			PageRequest  pageable = PageRequest.of(page, size, sort);
			return roomsRepository.findAll(pageable);
			}
		
	}

