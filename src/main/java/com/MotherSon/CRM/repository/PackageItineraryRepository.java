package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.PackageItinerary;

@Repository
public interface PackageItineraryRepository extends JpaRepository<PackageItinerary,Long>{

	}
