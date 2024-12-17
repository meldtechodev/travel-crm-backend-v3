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

import com.MotherSon.CRM.models.Sightseeing;

import com.MotherSon.CRM.security.services.SightseeingService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/sightseeing")
public class SightseeingController {
	
	
	@Autowired
	private SightseeingService sightseeingService;
	
	
	@GetMapping("/getby/{id}")
	public ResponseEntity<Sightseeing> getSightseeingById(@PathVariable Long id){
		Optional<Sightseeing> sightseeing = sightseeingService.getSightseeingById(id);
		return (ResponseEntity<Sightseeing>) sightseeing.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@GetMapping("/getAllSightseeing")
	public List<Sightseeing> getAllSightseeing(){
		return sightseeingService.getAllSightseeing();
	}
	
	
	
	@GetMapping("/getAll")
	public Page<Sightseeing> getSightseeing(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return sightseeingService.getSightseeing(page , size , sortDirection);
	}
	
	
	@PostMapping("/create")
	public Sightseeing addSightseeing(@RequestBody Sightseeing sightseeing) {
		return this.sightseeingService.addSightseeing(sightseeing);
	}
	
	
	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<Sightseeing> updateSightseeing(@PathVariable Long id , @RequestBody Sightseeing sightseeing){
		if(sightseeing != null)
		{
			Sightseeing si = new Sightseeing();
			
			si.setId(id);
			
			si.setTitle(sightseeing.getTitle());
			si.setIpaddress(sightseeing.getIpaddress());
			si.setStatus(sightseeing.isStatus());
			si.setIsdelete(sightseeing.isIsdelete());
			si.setCreatedby(sightseeing.getCreatedby());
			si.setModifiedby(sightseeing.getModifiedby());
			si.setCreatedDate(sightseeing.getCreatedDate());
			si.setModifiedDate(sightseeing.getModifiedDate());
			
		
		
			sightseeingService.updateSightseeing(si);
		return ResponseEntity.ok(si);
	}

	else
	{
		return ResponseEntity.notFound().build();
	}
	}
	
	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Sightseeing> deleteSightseeing(@PathVariable Long id){
		
		try
		{
			sightseeingService.findById(id);
			sightseeingService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
	}
	}
