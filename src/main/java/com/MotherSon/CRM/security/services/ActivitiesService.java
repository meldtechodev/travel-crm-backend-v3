package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Activities;
import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.repository.ActivitiesRepository;

@Service
public class ActivitiesService {
	
	
	@Autowired
	private ActivitiesRepository activitiesRepository;
	
	
	public Optional<Activities> getActivitiesById(Long id){
		return activitiesRepository.findById(id);
	}
	
	
public Page<Activities> getActivities(int page, int size, String sortDirection){
		
		Sort sort = Sort.by(Sort.Order.asc("title"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("title"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		
		return activitiesRepository.findAll(pageable);
			
			
			
		}
	
	
	public Activities addActivities(Activities activities) {
		return activitiesRepository.save(activities);
	}
	
	
	public Activities updateActivities(Activities ai) {
		return activitiesRepository.save(ai);
	}

}
