package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.DesignationModules;

@Repository
public interface DesignationModulesRepository extends JpaRepository<DesignationModules, Long> {

}
