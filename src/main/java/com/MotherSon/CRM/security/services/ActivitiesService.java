package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Activities;
import com.MotherSon.CRM.repository.ActivitiesRepository;

@Service
public class ActivitiesService {
	
	
	@Autowired
	private ActivitiesRepository activitiesRepository;
	
	
	public Optional<Activities> getActivitiesById(Long id){
		return activitiesRepository.findById(id);
	}
	
	
	public List<Activities> getAllActivities(){
		return activitiesRepository.findAll();
	}
	
	
	public Activities addActivities(Activities activities) {
		return activitiesRepository.save(activities);
	}
	
	
	public Activities updateActivities(Activities ai) {
		return activitiesRepository.save(ai);
	}

}
