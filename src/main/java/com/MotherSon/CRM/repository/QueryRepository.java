package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
	
	boolean existsByEmailId(String emailId);

	//boolean existsEmailId(String emailId); 
	
	boolean existsByContactNo(String contactNo);


}