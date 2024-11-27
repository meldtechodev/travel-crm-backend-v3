package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.PackageItinerary;
import com.MotherSon.CRM.models.PackageitineraryDetails;
import com.MotherSon.CRM.repository.PackageitineraryDetailsRepository;

@Service
public class PackageitineraryDetailsService {
	
	
	@Autowired
	private PackageitineraryDetailsRepository packageitinerarydetailsRepository;
	
	
	
	
	
	public PackageitineraryDetails addPackageitineraryDetails(PackageitineraryDetails packageitinerarydetails) {
		return packageitinerarydetailsRepository.save(packageitinerarydetails);
	}
	
	
	public PackageitineraryDetails updatePackageitineraryDetails(PackageitineraryDetails pid) {
		return packageitinerarydetailsRepository.save(pid);
	}
	
	
	public Optional<PackageitineraryDetails> getPackageitineraryDetailsById(Long id) {
		return packageitinerarydetailsRepository.findById(id);
	}
	
	
	public List<PackageitineraryDetails> getAllPackageitineraryDetails() {
		return packageitinerarydetailsRepository.findAll();
	}
	
	
	
	
	public void deletedById(long id) {
		packageitinerarydetailsRepository.deleteById(id);
	}
	
	public PackageItinerary findById(long id) {
		return null;
	}
}
