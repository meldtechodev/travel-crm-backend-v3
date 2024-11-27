package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Hotel;

@Repository
	public interface HotelServiceImpl extends JpaRepository<Hotel, Long> {
		
		//Hotel save(Hotel hotel);

//		List<Hotel> findByDestinationId(Long destinationid);
	//
//		List<Hotel> findByDestinationIdAndStar_ratings(Long ddestinationid);
	boolean existsByhname(String hname);  

	}