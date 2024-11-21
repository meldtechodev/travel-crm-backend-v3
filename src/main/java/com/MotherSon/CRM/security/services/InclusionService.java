package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Inclusion;
import com.MotherSon.CRM.repository.InclusionRepository;



@Service
public class InclusionService {

	@Autowired
	private InclusionRepository inclusionrepository;
	
	public Inclusion createInclusionser(Inclusion inclusion)
	{
		        
		System.out.print("helloservice");
		  Inclusion saveinclusionser =     inclusionrepository.save(inclusion);
		   return saveinclusionser;
		
	}
	
	public List<Inclusion>getallinclusionser()
	{
		    List<Inclusion> getinclusionse=inclusionrepository.findAll();
		    
		   return getinclusionse;
		
	}
}
