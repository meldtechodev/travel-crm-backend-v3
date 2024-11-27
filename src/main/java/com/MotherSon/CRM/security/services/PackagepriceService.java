package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.PackagePrice;
import com.MotherSon.CRM.repository.PackagepriceRepository;

@Service
public class PackagepriceService {
    
	@Autowired
	private PackagepriceRepository packagepricerepository;
	
	  public PackagePrice createpackagepriceser(PackagePrice packageprice)
	  {
		 PackagePrice packagepricesaves=  packagepricerepository.save(packageprice);
		return packagepricesaves;
		  
	  }
	  public List<PackagePrice> getallpackagepriceser()
	  {
		 List<PackagePrice>getallpackageprice= packagepricerepository.findAll();
		return getallpackageprice;
		  
	  }
	  
}
