package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.repository.ModulesRepository;

@Service
public class ModulesService {
	
	
	@Autowired
	private ModulesRepository modulesRepository;
	
	public Modules addModules(Modules modules) {
		Modules admodule = modulesRepository.save(modules);
		return admodule;
	}
	
	
	public List<Modules> getAllModules(){
		List<Modules> allmodule = modulesRepository.findAll();
		return allmodule;
	}

}
