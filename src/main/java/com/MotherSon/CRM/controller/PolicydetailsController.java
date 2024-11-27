package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.PolicyDetails;
import com.MotherSon.CRM.security.services.PolicydetailsService;

@RestController
@RequestMapping("Motherson/crm/v1/policydetails")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PolicydetailsController {
    
	@Autowired
	
	private PolicydetailsService policydetaiservice;
	@PostMapping("/create")
	public PolicyDetails createpolicydetailscon(@RequestBody PolicyDetails policydetails)
	{
		PolicyDetails createpolicydeta=policydetaiservice.createpolicydetailsser(policydetails);
		
		return createpolicydeta;
		
	}
	 
	@GetMapping("/getall")
	public List< PolicyDetails> getallpolicydetails()
	{
		 List<PolicyDetails> getpolicydetailscon=policydetaiservice.getallpolicydetailsser();
		return getpolicydetailscon;
		
	}
	
	
	
	
}
