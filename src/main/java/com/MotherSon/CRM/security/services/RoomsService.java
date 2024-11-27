package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.repository.RoomsRepository;

@Service
	public class RoomsService {
		
		
		@Autowired
		private RoomsRepository roomsRepository;
		
		
		public Rooms addRooms(Rooms rooms) {
			return roomsRepository.save(rooms);
		}
		public List<Rooms> getAllRoom() {
			return roomsRepository.findAll();
		}
		
	}

