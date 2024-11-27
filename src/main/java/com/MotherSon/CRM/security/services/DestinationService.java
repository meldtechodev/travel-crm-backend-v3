package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.repository.CountryRepository;
import com.MotherSon.CRM.repository.DestinationRepository;
//import com.ms.jwt.repository.destination.DestinationRepository;

 @Service
public class DestinationService<Country> {
	
	@Autowired
	private DestinationRepository destinationrepository;
	
//	@Autowired
//	private CountryRepository countryrepository;
	
	
	public Destination createddestination( Destination destination){
		return destinationrepository.save(destination);
	}
	
	public boolean existsByDestinationName(String destinationName)
	{
		return destinationrepository.existsByDestinationName(destinationName);
	}
	
	public List<Destination> getAllDestination(){
		return destinationrepository.findAll();
		
	}
	
	public Optional <Destination> getDestinationsById(long id)	{
		return destinationrepository.findById(id);
	}
	
//	public Optional<Destination> getDestinationByMasterKey(String masterKey) {
//	        return destinationrepository.findByMasterkey(masterKey);
//	 }
	
	public void deleteDestination(Long id)	{
		destinationrepository.deleteById(id);
	}
	
//	 public Destination updateDestinationByid(Long id,Destination destination)
//	 {
//		
//		  Optional<Destination> destiDetail=      destinationrepository.findById(id);
//		  
//		  {
//			  
//			  if(destiDetail.isPresent())
//			  {
//				   Destination destinationdetail=  destiDetail.get();
//				 //  destinationdetail.setCountryName(destination.getCountryName());
//				 //  destinationdetail.setStateName(destination.getStateName());
//				   destinationdetail.setStatus(destination.getStatus());
//				 //  destinationdetail.setKeyAttraction(destination.getKeyAttraction());
//				   destinationdetail.setD_image(destination.getD_image());
//				  // destinationdetail.setMasterkey(destination.getMasterkey());
//				  // destinationdetail.setIpAddress(destination.getIpAddress());
//				    return destinationrepository.save(destinationdetail);
//			  }
//			   else
//			   {
//				   new RuntimeException("Destination not found with id " + id);
//
//			   }
//				  
//
//				  
//		
//		  }
//		return destination;
//	 }
	
	public Destination updateDestinationById(Long id, Destination destinationDetails) {
        Destination existingDestination = destinationrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
 
        existingDestination.setDestinationName(destinationDetails.getDestinationName());
        
        existingDestination.setD_image(destinationDetails.getD_image());
        existingDestination.setIpaddress(destinationDetails.getIpaddress());
        existingDestination.setStatus(destinationDetails.isStatus());
        existingDestination.setCreated_date(destinationDetails.getCreated_date());
        existingDestination.setModified_date(destinationDetails.getModified_date());
        existingDestination.setCreated_by(destinationDetails.getCreated_by());
        existingDestination.setModified_by(destinationDetails.getModified_by());
        existingDestination.setIsdelete(destinationDetails.isIsdelete());
        existingDestination.setKeyofattractions(destinationDetails.getKeyofattractions());
        return destinationrepository.save(existingDestination);
    }
 
	
	public Destination getDestinationById(Long id) {
        return destinationrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }

	


}
