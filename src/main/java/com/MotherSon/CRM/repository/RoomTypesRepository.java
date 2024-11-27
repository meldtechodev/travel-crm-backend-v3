package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.RoomTypes;

@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes,Long>{
	}