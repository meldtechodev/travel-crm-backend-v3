package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.PackageTheme;
import com.MotherSon.CRM.repository.PackageThemeRepository;

@Service
public class PackageThemeService {
	
	@Autowired
	
	private PackageThemeRepository packagethemerepository;

	public PackageTheme createpackagethemeservice(PackageTheme packagethemeser)
	{
		         PackageTheme packagethemecreateser= packagethemerepository.save(packagethemeser);
		return packagethemecreateser;
		
	}
	
	public List<PackageTheme> getallpackagethemeser()
	{
		List<PackageTheme> getallpackagethemerep =packagethemerepository.findAll();
		return getallpackagethemerep;
		
	}
	  
}
