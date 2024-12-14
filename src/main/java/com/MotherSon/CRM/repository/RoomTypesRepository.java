package com.MotherSon.CRM.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.RoomTypes;

@Repository
public interface RoomTypesRepository extends JpaRepository<RoomTypes,Long>{
	
	List<RoomTypes> findByHotelId(Long hotelId);
	
	Optional<RoomTypes> findById(Long id);
	 
	}