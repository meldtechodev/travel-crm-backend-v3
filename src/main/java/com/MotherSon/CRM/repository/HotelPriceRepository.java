package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.HotelPrice;

@Repository
	public interface HotelPriceRepository extends JpaRepository<HotelPrice, Long> {

		 //void addHotelPrice(hotelprice);

	}
