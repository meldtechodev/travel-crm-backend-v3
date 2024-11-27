package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Country;

@Repository

public interface CountryRepository extends JpaRepository<Country,Long> {



	
	    

  // Country  findByKey(String masterKey);
	
	boolean existsByCountryName(String countryName); 
}
