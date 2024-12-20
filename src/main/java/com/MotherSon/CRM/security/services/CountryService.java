package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.repository.CountryRepository;

import jakarta.persistence.EntityExistsException;
//import com.ms.jwt.repository.country.CountryRepository;
@Service

public class CountryService {

	@Autowired
    private CountryRepository countryRepository;

	public List<Country> getAllCountries() {
        // Fetch all countries
        List<Country> countries = countryRepository.findAll();
        
        // Filter countries where isDelete is false (not deleted)
        return countries.stream()
                        .filter(country -> !country.isIsdelete())
                        .collect(Collectors.toList());
        }
	
	
	public Page<Country> getCountry(int page, int size, String sortDirection){
		
		Sort sort = Sort.by(Sort.Order.asc("countryName"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("countryName"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		
		return countryRepository.findAll(pageable);
			
			
			
		}
	
        
    

    public Optional<Country> getCountrysById(Long id) {
        return countryRepository.findById(id);
    }

//    public Optional<Country> getCountryByMasterKey(String masterKey) {
//        return countryRepository.findByMasterKey(masterKey);
//    }
    public boolean existsByCountryName(String countryName) {
        return countryRepository.existsByCountryName(countryName);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

//    public Country updateCountryById(Long id, Country countryDetails) {
//        Country existingCountry = countryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Country not found"));
// 
//        existingCountry.setCountryName(countryDetails.getCountryName());
//        existingCountry.setCode(countryDetails.getCode());
//        existingCountry.setCimage(countryDetails.getCimage());
//        existingCountry.setIpAddress(countryDetails.getIpAddress());
//        existingCountry.setStatus(countryDetails.isStatus());
//        existingCountry.setCreateddate(countryDetails.getCreateddate());
//        existingCountry.setModifieddate(countryDetails.getModifieddate());
//        existingCountry.setCreatedby(countryDetails.getCreatedby());
//        existingCountry.setModifiedby(countryDetails.getModifiedby());
//        existingCountry.setIsdelete(countryDetails.isIsdelete());
//        
//        return countryRepository.save(existingCountry);
//    }
    
    public Country updateCountryById(Long id, Country countryDetails) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
 
        existingCountry.setCountryName(countryDetails.getCountryName());
        existingCountry.setCode(countryDetails.getCode());
        existingCountry.setCimage(countryDetails.getCimage());
        existingCountry.setIpAddress(countryDetails.getIpAddress());
        existingCountry.setStatus(countryDetails.isStatus());
        existingCountry.setCreateddate(countryDetails.getCreateddate());
        existingCountry.setModifieddate(countryDetails.getModifieddate());
        existingCountry.setCreatedby(countryDetails.getCreatedby());
        existingCountry.setModifiedby(countryDetails.getModifiedby());
        existingCountry.setIsdelete(countryDetails.isIsdelete());
        
        return countryRepository.save(existingCountry);
    }
 
	
	public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }
 
 
 
	
//	public Country getCountryById(Long id) {
//        return countryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Country not found"));
//    }



	public void deleteCountry(Long id) {
        // Check if the country exists before attempting to delete
        if (!countryRepository.existsById(id)) {
            throw new EntityExistsException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }
}

	
//	public Country getCountryById(Long id) {
//        return countryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Country not found"));
//    }

	

