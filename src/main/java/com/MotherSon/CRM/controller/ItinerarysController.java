package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Itinerarys;
import com.MotherSon.CRM.repository.HotelServiceImpl;
import com.MotherSon.CRM.security.services.ItinerarysService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/itinerarys")
public class ItinerarysController {
	
	
	@Autowired
	private ItinerarysService itinerarysService;
	@Autowired HotelServiceImpl hotelrepository;
	
	
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Itinerarys> getItinerarysById(@PathVariable Long id){
		Optional<Itinerarys> it = itinerarysService.getItinerarysById(id);
		return it.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	
	
//	@GetMapping("/getAll")
//	public List<Itinerarys> getAllItinerarys(){
//		return itinerarysService.getAllItinerarys();
//	}
	
	
	@GetMapping("/getAll")
	public Page<Itinerarys> getItinerarys(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return itinerarysService.getItinerarys(page , size , sortDirection);
	}
	
	
	
	
	
	//@PostMapping(consumes = "application/json", produces = "application/json")
//	@PostMapping("/create")
//	public ResponseEntity<Itinerarys> createItinerary(@RequestBody Itinerarys itinerary) {
//	    try {
//	        // Handle the incoming itinerary and return a response
//	        return ResponseEntity.ok(itinerary);
//	    } catch (Exception e) {
//	        e.printStackTrace(); // Log the error
//	        return ResponseEntity.badRequest().build();
//	    }
//	}
	
	
	
//	//@PostMapping("/create")
//	@PostMapping(value = "/your-endpoint", consumes = "application/json", produces = "application/json")
//	public Itinerarys addItinerarys(@RequestBody Itinerarys itinerarys) {
//		return this.itinerarysService.addItinerarys(itinerarys);
//	}
	
	
	//@PostMapping(value = "/itineraries", consumes = MediaType.APPLICATION_JSON_VALUE)
	
	
		@PostMapping("/create")
		public ResponseEntity<?> addItinerarys(@RequestBody Itinerarys itinerarys) {
		    // Validate hotelOptionIds
		    for (Long hotelOptionId : itinerarys.getHotelOptionIds()) {
		        if (!hotelrepository.existsById(hotelOptionId)) {
		            throw new IllegalArgumentException("HotelOption ID " + hotelOptionId + " does not exist");
		        }
		    }

		    Itinerarys createdItinerary = this.itinerarysService.addItinerarys(itinerarys);
		    return new ResponseEntity<>("itinarys created sucessfully",HttpStatus.CREATED);
		}
	
	
	
	
	@PutMapping("/updateby{id}")
	public ResponseEntity<Itinerarys> updateItinerarys(@PathVariable long id , @RequestBody Itinerarys itinerarys ){
		if(itinerarys != null)
		{
			Itinerarys it = new Itinerarys();
			it.setId(id);
			////it.setDaynumber(itinerarys.getDaynumber());
			//it.setCityname(itinerarys.getCityname());
			it.setDaytitle(itinerarys.getDaytitle());
			it.setProgram(itinerarys.getProgram());
			it.setMeals(itinerarys.getMeals());
			//it.setTransportid(itinerarys.getTransportid());
			it.setCreatedby(itinerarys.getCreatedby());
			it.setModifiedby(itinerarys.getModifiedby());
			it.setIpaddress(itinerarys.getIpaddress());
			it.setStatus(itinerarys.isStatus());
			it.setIsdelete(itinerarys.isIsdelete());
			it.setCreateddate(itinerarys.getCreateddate());
			it.setModifieddate(itinerarys.getModifieddate());
		
			
			itinerarysService.updateItinerarys(it);
			return ResponseEntity.ok(it);
			
		}
		else
		{
			return ResponseEntity.notFound().build();
			
		}}
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Itinerarys> deleteItinerarys(@PathVariable Long id){
		
		try
		{
			itinerarysService.findById(id);
			itinerarysService.deleteById(id);
		}
		
		finally
		{
			
			return ResponseEntity.notFound().build();
		}
	}
	

}