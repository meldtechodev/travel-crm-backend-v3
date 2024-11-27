package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Season;
import com.MotherSon.CRM.security.services.SeasonService;

@RestController
@RequestMapping("Motherson/crm/v1/season")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SeasonController {
	
	
	
	@Autowired
	private SeasonService seasonService;
	
	
	
	
	@GetMapping("/getAll")
	public List<Season> getAllSeason(){
		return seasonService.getAllSeason();
	}
	
	
	@PostMapping("/create")
    public Season addSeason(@RequestBody Season season) {
  	return this.seasonService.addSeason(season);

}}