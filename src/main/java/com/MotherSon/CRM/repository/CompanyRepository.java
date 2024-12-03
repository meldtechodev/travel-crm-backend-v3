package com.MotherSon.CRM.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.models.Country;

 

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{
	Page<Company> findAll(Pageable pageable);
	boolean existsByCompanyname(String companyname); 
}


	

