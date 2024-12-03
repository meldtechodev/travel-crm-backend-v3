package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	
	public Page<Inclusion> getInclusion(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("inclusionname"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("inclusionname"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		return inclusionrepository.findAll(pageable);
		}
		
	}

