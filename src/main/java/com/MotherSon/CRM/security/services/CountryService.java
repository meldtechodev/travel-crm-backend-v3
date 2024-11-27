package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

//import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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
    	return countryRepository.findAll();
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

	

