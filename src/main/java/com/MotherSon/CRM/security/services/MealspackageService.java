package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Itinerarys;
import com.MotherSon.CRM.models.Mealspackage;
import com.MotherSon.CRM.repository.MealspackageRepository;

@Service
public class MealspackageService {
	
	
	@Autowired
	private MealspackageRepository mealspackageRepository;
	
	
	
//	public List<Mealspackage> getAllMealspackage(){
//	return mealspackageRepository.findAll();
//	
//}

//public List<Mealspackage> getAllMealspackage() {
//	return mealspackageRepository.findAll();
//}
	
	public Page<Mealspackage> getMealspackage(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("mealstypeCode"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("mealstypeCode"));
		}
		
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		return mealspackageRepository.findAll(pageable);
	}


public Optional<Mealspackage> getMealspackageById(Long id){
	return mealspackageRepository.findById(id);
}


public Mealspackage addMealspackage(Mealspackage mealspackage) {
	return mealspackageRepository.save(mealspackage);
}

}

