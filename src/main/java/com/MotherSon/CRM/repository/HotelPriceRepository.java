package com.MotherSon.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.HotelPrice;

@Repository
	public interface HotelPriceRepository extends JpaRepository<HotelPrice, Long> {

	Optional<HotelPrice> findByHotel_Id(Long hotelId);

		 //void addHotelPrice(hotelprice);

	}
