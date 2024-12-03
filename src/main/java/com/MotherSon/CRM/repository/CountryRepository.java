package com.MotherSon.CRM.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Departments;

@Repository

public interface CountryRepository extends JpaRepository<Country,Long> {



	
	Page<Country> findAll(Pageable pageable);

  // Country  findByKey(String masterKey);
	
	boolean existsByCountryName(String countryName); 
}
