package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Itinerarys;

@Repository
public interface ItinerarysRepository extends JpaRepository<Itinerarys, Long> {

}
