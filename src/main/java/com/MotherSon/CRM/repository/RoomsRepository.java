package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Long>{
		
}