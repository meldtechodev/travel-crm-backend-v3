package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Inclusion;

@Repository
public interface InclusionRepository extends JpaRepository<Inclusion,Long> {
    
}
