package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.PackageItinerary;
import com.MotherSon.CRM.repository.PackageItineraryRepository;

@Service
	public class PackageItineraryService {
		
		
		@Autowired
		private PackageItineraryRepository packageItineraryRepository;
		
		
//		public List<PackageItinerary> getAllPackageItinerary() {
//			return packageItineraryRepository.findAll();
//		}
		
		
		public Page<PackageItinerary> getPackageItinerary(int page , int size , String sortDirection){
			Sort sort = Sort.by(Sort.Order.asc("cityname"));
			
			if("desc".equalsIgnoreCase(sortDirection)) {
				sort = Sort.by(Sort.Order.desc("cityname"));
			}
			
			
			PageRequest pageable = PageRequest.of(page, size, sort);
			return packageItineraryRepository.findAll(pageable);
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

