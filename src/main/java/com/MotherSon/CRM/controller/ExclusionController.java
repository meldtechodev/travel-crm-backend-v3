package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Exclusion;
import com.MotherSon.CRM.security.services.Exclusionservice;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/exclusion")
public class ExclusionController {
     
	@Autowired 
	private Exclusionservice exclusionservice;
	
	@PostMapping("/create")
	 public Exclusion createexclusion(@RequestBody Exclusion exclusion)
	 {
		      Exclusion savexclusioncon= exclusionservice.createexclusionser(exclusion);
		return savexclusioncon;
		 
	 }
	 
	@GetMapping("/getall")
	public List<Exclusion> getallexclusion()
	{
		    List<Exclusion>getexclusioncon= exclusionservice.getallExclusionser();
		return getexclusioncon;
		
	}
	
	
}
