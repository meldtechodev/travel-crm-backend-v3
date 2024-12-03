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


import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Season;
import com.MotherSon.CRM.security.services.SeasonService;

@RestController
@RequestMapping("Motherson/crm/v1/season")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SeasonController {
	
	
	
	@Autowired
	private SeasonService seasonService;
	
	
	
	
//	@GetMapping("/getAll")
//	public List<Season> getAllSeason(){
//		return seasonService.getAllSeason();
//	}
	
	
	@GetMapping("/getAll")
	public Page<Season> getSeason(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return seasonService.getSeason(page , size , sortDirection);
	}
	
	
//	@PostMapping("/create")
//    public Season addSeason(@RequestBody Season season) {
//  	return this.seasonService.addSeason(season);
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> addSeason(@RequestBody Season season){
		try
		{
			Season cseas = seasonService.addSeason(season);
			return new ResponseEntity<>(cseas, HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		
		}
	}
	
	

}