package com.MotherSon.CRM.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Inclusion;
import com.MotherSon.CRM.security.services.InclusionService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/inclusion")

public class InclusionController {
	
	@Autowired 
	 
	private InclusionService inclusionservice;
   
	@PostMapping("/create")
	 public Inclusion createInclusion(@RequestBody Inclusion inclusion)
	 {
		System.out.print("hello"+inclusion);
		  Inclusion savinclusion=inclusionservice.createInclusionser(inclusion);
		return savinclusion;
		 
	 }
	 
	@GetMapping("/getall")
	 public List<Inclusion> getAllInclusion()
	 {
		     List<Inclusion>getinclusion=  inclusionservice.getallinclusionser();
		return getinclusion;
		 
	 }
	
}
