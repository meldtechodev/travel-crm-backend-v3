package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Mealspackage;
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
	
//	public List<PackageTheme> getallpackagethemeser()
//	{
//		List<PackageTheme> getallpackagethemerep =packagethemerepository.findAll();
//		return getallpackagethemerep;
//		
//	}
	
	
	
	public Page<PackageTheme> getPackageTheme(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("title"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("title"));
		}
		
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		return packagethemerepository.findAll(pageable);
	}
	  
}
