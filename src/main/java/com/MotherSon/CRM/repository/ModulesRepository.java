package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Modules;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {

}
