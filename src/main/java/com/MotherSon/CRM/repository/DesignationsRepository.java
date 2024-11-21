package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Designations;

@Repository
public interface DesignationsRepository extends JpaRepository<Designations, Long> {
	
	boolean existsByDesignationName(String designationName);

}
