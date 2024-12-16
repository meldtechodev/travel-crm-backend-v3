package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.DesignationModules;

@Repository
public interface DesignationModulesRepository extends JpaRepository<DesignationModules, Long> {
	
	 // Custom query to fetch by designation_id
    @Query("SELECT dm FROM DesignationModules dm WHERE dm.designations.id = ?1")
    List<DesignationModules> findByDesignationId(Long designationId);

}
