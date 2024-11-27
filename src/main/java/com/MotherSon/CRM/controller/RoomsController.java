package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.security.services.RoomsService;

@RestController
@RequestMapping("Motherson/crm/v1/rooms")
@CrossOrigin(origins = "*", maxAge = 3600)
	public class RoomsController {
		
		@Autowired
		private RoomsService roomsService;
		
		
		
		
	    @PostMapping("/create")
	      public Rooms addRooms(@RequestBody Rooms rooms) {
	    	return this.roomsService.addRooms(rooms);
	    }
	    @GetMapping("/getAll")
		public List<Rooms> getAllRoom(){
			return roomsService.getAllRoom();
		}
	 }

