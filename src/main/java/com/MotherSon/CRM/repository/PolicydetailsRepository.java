package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.PolicyDetails;

@Repository
public interface PolicydetailsRepository extends JpaRepository<PolicyDetails,Long>{
     
	
	
}