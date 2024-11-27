package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Exclusion;
import com.MotherSon.CRM.repository.ExclusionRepository;

@Service
public class Exclusionservice {
  
	@Autowired
	private ExclusionRepository exclusionrepository;
	
	public Exclusion createexclusionser(Exclusion exclusion)
	{
		           
		      
		Exclusion exclusionser= exclusionrepository.save(exclusion);
		
		       return exclusionser;
	}
	
	 public List<Exclusion> getallExclusionser()
	 {
		 
		    List<Exclusion> getexclusionservice=    exclusionrepository.findAll();
		
		    return getexclusionservice;
		 
	 }
	
	
}
