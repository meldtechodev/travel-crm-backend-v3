package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Destination;

@Repository

public interface DestinationRepository extends JpaRepository<Destination,Long > {

	  
	
	boolean existsByDestinationName(String destinationName);
	   
}