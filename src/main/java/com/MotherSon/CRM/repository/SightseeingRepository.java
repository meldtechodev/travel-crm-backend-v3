package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Sightseeing;

@Repository
public interface SightseeingRepository extends JpaRepository<Sightseeing, Long> {

}
