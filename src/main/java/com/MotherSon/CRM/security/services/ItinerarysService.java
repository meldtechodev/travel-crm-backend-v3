package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Itinerarys;
import com.MotherSon.CRM.repository.ItinerarysRepository;

@Service
public class ItinerarysService {
	
	
	@Autowired
	private ItinerarysRepository itinerarysRepository;
	
	
	public Optional<Itinerarys> getItinerarysById(Long id) {
		return itinerarysRepository.findById(id);
	}
	
	
	public Itinerarys addItinerarys(Itinerarys itinerarys) {
		return itinerarysRepository.save(itinerarys);
	}
	
	
	
//	public List<Itinerarys> getAllItinerarys() {
//		return itinerarysRepository.findAll();
//	}
	
	
	public Page<Itinerarys> getItinerarys(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("daytitle"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("daytitle"));
		}
		
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		return itinerarysRepository.findAll(pageable);
	}
	
	
	public Itinerarys updateItinerarys(Itinerarys it) {
		return itinerarysRepository.save(it);
	}
	
	
	
	public void deleteById(long id) {
		 itinerarysRepository.deleteById(id);
		
	}
	
	public Itinerarys findById(long id) {
		return null;
	}
	

}