package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	boolean existsByEmailId(String emailId);
	
	
	boolean existsByContactNo(String contactNo);
	
	
	boolean existsByAdharNo(String adharNo);
	
	
	boolean existsByPassportId(String passportId);
   

}