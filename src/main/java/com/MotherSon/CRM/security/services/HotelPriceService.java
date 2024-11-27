package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.HotelPrice;
import com.MotherSon.CRM.repository.HotelPriceRepository;

@Service
	public class HotelPriceService {
		
		
		@Autowired
	    private HotelPriceRepository hotelpriceRepository;
		


		public HotelPrice addHotelPrice(HotelPrice hotelprice) {
			return hotelpriceRepository.save(hotelprice);
		}
		
		public List<HotelPrice> getAllHotelPrice(){
			return hotelpriceRepository.findAll();
		}
		
		public Optional<HotelPrice> getHotelPriceById(Long id){
			return hotelpriceRepository.findById(id);
		}
		
		public void deleteById(Long id) {
			 hotelpriceRepository.deleteById(id);
		}
		
		public HotelPrice findById(Long id) {
			return null;
		}
		
		

	}

