package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.PackageitineraryDetails;
import com.MotherSon.CRM.repository.ActivitiesRepository;
import com.MotherSon.CRM.repository.HotelServiceImpl;
import com.MotherSon.CRM.repository.MealspackageRepository;
import com.MotherSon.CRM.repository.SightseeingRepository;
import com.MotherSon.CRM.security.services.PackageitineraryDetailsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/packageitinerarydetails")
public class PackageitineraryDetailsController {
	
	
	@Autowired
	private PackageitineraryDetailsService packageitinerarydetailsService;
	
	@Autowired
	private HotelServiceImpl hotelrepository;
	
	@Autowired
	private SightseeingRepository sightseeingrepository;
	
	@Autowired
	private ActivitiesRepository activitiesRepository;
	
	
	@Autowired
	private MealspackageRepository mealspackageRepository;
	
	
	
	
	@GetMapping("/getby/{id}")
    public ResponseEntity<PackageitineraryDetails> PackageitineraryDetails(@PathVariable Long id) {
        Optional<PackageitineraryDetails> packageItineraryDetails = packageitinerarydetailsService.getPackageitineraryDetailsById(id);
        if (packageItineraryDetails.isPresent()) {
            return ResponseEntity.ok(packageItineraryDetails.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	
	@GetMapping("/getAll")
	public List<PackageitineraryDetails> getAllPackageitineraryDetails(){
		return packageitinerarydetailsService.getAllPackageitineraryDetails();
	}
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> addPackageItineraryDetails(@RequestBody PackageitineraryDetails packageitineraryDetails) {
	    validateHotelAndSightseeingIds(packageitineraryDetails);
	    
	    PackageitineraryDetails packageItineraryDetail = packageitinerarydetailsService.addPackageitineraryDetails(packageitineraryDetails);
	    return new ResponseEntity<>("Package itinerary details created successfully", HttpStatus.CREATED);
	}

	// Helper method for validation
	private void validateHotelAndSightseeingIds(PackageitineraryDetails packageitineraryDetails) {
	    // Validate Hotel Option IDs
//	    for (Long hotelOptionId : packageitineraryDetails.getHotelOptionIds()) {
//	        if (!hotelrepository.existsById(hotelOptionId)) {
//	            throw new IllegalArgumentException("HotelOption ID " + hotelOptionId + " does not exist");
//	        }
//	    }
	    
	    // Validate Sightseeing IDs
	    for (Long sightseeingId : packageitineraryDetails.getSightseeingIds()) {
	        if (!sightseeingrepository.existsById(sightseeingId)) {
	            throw new IllegalArgumentException("Sightseeing ID " + sightseeingId + " does not exist");
	        }
	    }
	    
	    
	    
	    for (Long activitiesId : packageitineraryDetails.getActivitiesIds()) {
	        if (!activitiesRepository.existsById(activitiesId)) {
	            throw new IllegalArgumentException("Activities ID " + activitiesId + " does not exist");
	        }
	    }
	    
	    
	    for (Long mealspackageId : packageitineraryDetails.getMealspackageIds()) {
	        if (!mealspackageRepository.existsById(mealspackageId)) {
	            throw new IllegalArgumentException("Mealspackage ID " + mealspackageId + " does not exist");
	        }
	    }
	    
	    
	}

	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<PackageitineraryDetails> updatePackageitineraryDetails(@PathVariable long id , @RequestBody PackageitineraryDetails packageitineraryDetails ){
		if(packageitineraryDetails != null)
		{
			PackageitineraryDetails pid = new PackageitineraryDetails();
			pid.setId(id);
			pid.setIpaddress(packageitineraryDetails.getIpaddress());
			pid.setCategory(packageitineraryDetails.getCategory());
			pid.setStatus(packageitineraryDetails.isStatus());
			pid.setIsdelete(packageitineraryDetails.isIsdelete());
			pid.setCreatedby(packageitineraryDetails.getCreatedby());
			pid.setModifiedby(packageitineraryDetails.getModifiedby());
			pid.setCreatedDate(packageitineraryDetails.getCreatedDate());
			pid.setModifiedDate(packageitineraryDetails.getModifiedDate());
			
		
			
			packageitinerarydetailsService.updatePackageitineraryDetails(pid);
			return ResponseEntity.ok(pid);
			
		}
		else
		{
			return ResponseEntity.notFound().build();
			
		}}
	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<PackageitineraryDetails> deletePackageitineraryDetails(@PathVariable long id){
		
		try
		{
			packageitinerarydetailsService.findById(id);
			packageitinerarydetailsService.deletedById(id);
		return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
	
}}