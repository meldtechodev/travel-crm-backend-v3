package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

public List<Mealspackage> getAllMealspackage() {
	return mealspackageRepository.findAll();
}
	



public Optional<Mealspackage> getMealspackageById(Long id){
	return mealspackageRepository.findById(id);
}


public Mealspackage addMealspackage(Mealspackage mealspackage) {
	return mealspackageRepository.save(mealspackage);
}

}

