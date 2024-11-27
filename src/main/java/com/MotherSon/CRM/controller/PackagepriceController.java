package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.PackagePrice;
import com.MotherSon.CRM.repository.PackagepriceRepository;
import com.MotherSon.CRM.repository.PkgRepository;
import com.MotherSon.CRM.security.services.PackagepriceService;

@RestController
@RequestMapping("Motherson/crm/v1/packageprice")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PackagepriceController {
	@Autowired
	private PackagepriceService packagepriceservice;
	@Autowired
	private PkgRepository pkgrepository;
	@Autowired
	private PackagepriceRepository packagepricerepository;
	@PostMapping("/create")
     public PackagePrice createpackagepricecon( @RequestBody PackagePrice packageprice)
     {
    	 
		 // Step 1: Check if the packid is null or the package doesn't exist
        if (packageprice.getPackid() == null || !pkgrepository.existsById(packageprice.getPackid())) {
            throw new IllegalArgumentException("packid does not exist");
        }

        // Check if the packid is already used in another PackageItinerary (same packid should not be used twice)
        if (packagepricerepository.existsByPackid(packageprice.getPackid())) {
            throw new IllegalArgumentException("The packid is already used in packageprice");
        }

    	 PackagePrice packagepricesavcon=packagepriceservice.createpackagepriceser(packageprice);
		return packagepricesavcon;
    	 
     }
	 @GetMapping("/getall")
	public List<PackagePrice>getallpackagepricecon()
	{
		List<PackagePrice> getallpackagepriceco=packagepriceservice.getallpackagepriceser();
		return getallpackagepriceco;
		
	}
	
}
