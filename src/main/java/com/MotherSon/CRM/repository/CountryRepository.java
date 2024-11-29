package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 

import com.MotherSon.CRM.models.Country;

@Repository

public interface CountryRepository extends JpaRepository<Country,Long> {

  // Country  findByKey(String masterKey);
	
	Page<Country> findAll(Pageable pageable);
	
	boolean existsByCountryName(String countryName); 
}
