package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.security.services.ModulesService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/modules")
public class ModulesController {
	
	
	@Autowired
	private ModulesService modulesService;
	
	
	@GetMapping("/getall")
	public List<Modules> getAllModules(){
		
		List<Modules> getmodule = modulesService.getAllModules();
		return getmodule;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<String> addModules(@RequestBody Modules modules){
		Modules admodule = modulesService.addModules(modules);
		return ResponseEntity.status(HttpStatus.CREATED).body(" Modules is Created ");
	}
	
	
	

}
