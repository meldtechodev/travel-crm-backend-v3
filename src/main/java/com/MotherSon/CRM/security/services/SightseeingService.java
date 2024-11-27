package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Sightseeing;
import com.MotherSon.CRM.repository.SightseeingRepository;

@Service
public class SightseeingService {
	
	
	@Autowired
	private SightseeingRepository sightseeingRepository;
	
	
	public Sightseeing addSightseeing(Sightseeing sightseeing) {
		return sightseeingRepository.save(sightseeing);
	}
	
	
	public Optional<Sightseeing> getSightseeingById(Long id){
		return sightseeingRepository.findById(id);
	}
	
	
	public List<Sightseeing> getAllSightseeing(){
		return sightseeingRepository.findAll();
	}
	
	
	public Sightseeing updateSightseeing(Sightseeing si) {
		return sightseeingRepository.save(si);
	}

	
	public void deleteById(long id) {
		sightseeingRepository.deleteById(id);
	}

	public Sightseeing findById(Long id) {
		return null;
		
	}
	
	
}
