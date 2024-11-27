package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.PackageItinerary;
import com.MotherSon.CRM.repository.PackageItineraryRepository;

@Service
	public class PackageItineraryService {
		
		
		@Autowired
		private PackageItineraryRepository packageItineraryRepository;
		
		
		public List<PackageItinerary> getAllPackageItinerary() {
			return packageItineraryRepository.findAll();
		}

		
		public Optional<PackageItinerary> getPackageItineraryById(Long id) {
			return packageItineraryRepository.findById(id);
		}
		
		
		public PackageItinerary addPackageItinerary(PackageItinerary packageItinerary) {
			return packageItineraryRepository.save(packageItinerary);
		}
		
		
		public PackageItinerary updatePackageItinerary(PackageItinerary pi) {
			return packageItineraryRepository.save(pi);
		}
		
		
		public void deletedById(long id) {
			packageItineraryRepository.deleteById(id);
		}
		

		public PackageItinerary findById(long id) {
			return null;
		}
		
		

	}

