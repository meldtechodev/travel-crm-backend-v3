package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Rooms;
import com.MotherSon.CRM.security.services.RoomsService;

@RestController
@RequestMapping("Motherson/crm/v1/rooms")
@CrossOrigin(origins = "*", maxAge = 3600)
	public class RoomsController {
		
		@Autowired
		private RoomsService roomsService;
		
		
		
		
//	    @PostMapping("/create")
//	      public Rooms addRooms(@RequestBody Rooms rooms) {
//	    	return this.roomsService.addRooms(rooms);
//	    }
		
		
		
		
		@PostMapping("/create")
		public ResponseEntity<?> addRooms(@RequestBody Rooms rooms){
			try
			{
				Rooms croom = roomsService.addRooms(rooms);
				return new ResponseEntity<>(croom, HttpStatus.CREATED);
			}catch(IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
		}
		
//	    @GetMapping("/getAll")
//		public List<Rooms> getAllRoom(){
//			return roomsService.getAllRoom();
//		}
	    
	    
	    @GetMapping("/getAll")
		public Page<Rooms> getRooms(
				@RequestParam(value = "page" , defaultValue = "0") int page,
				@RequestParam(value = "size" , defaultValue = "10") int size,
				@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
				){
			return roomsService.getRooms(page , size , sortDirection);
		}
	    
	 }

