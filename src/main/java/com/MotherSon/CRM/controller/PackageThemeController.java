package com.MotherSon.CRM.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.PackageTheme;
import com.MotherSon.CRM.security.services.PackageThemeService;

@RestController
@RequestMapping("Motherson/crm/v1/packageTheme")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PackageThemeController {
   
	@Autowired
	private PackageThemeService packagethemeservice;
	
	@PostMapping("/create")
	public ResponseEntity<?> createpackagethem(@RequestBody PackageTheme packagetheme) 
	{
		    PackageTheme packageThemecreatecon= packagethemeservice.createpackagethemeservice(packagetheme);
		
		    return new ResponseEntity<>("packagetheme is created sucessfully",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getall")
	public List<PackageTheme> getallpackagethem()
	{
		  List<PackageTheme>  getallpackagethemecon= packagethemeservice.getallpackagethemeser();
		return getallpackagethemecon ;
		
	}
}
