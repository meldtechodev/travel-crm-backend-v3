package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.State;

@Repository

public interface CountryRepository extends JpaRepository<Country,Long> {
	
	List<Country> findByIsdeleteFalse();
	
	Page<Country> findAll(Pageable pageable);
	
	boolean existsByCountryName(String countryName); 

}
