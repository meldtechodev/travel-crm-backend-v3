package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Vendor;
import com.MotherSon.CRM.repository.VendorRepository;

@Service
public class VendorService {
	
	
	@Autowired
	private VendorRepository vendorRepository;
	
	
	public List<Vendor> getAllVendor(){
		return vendorRepository.findAll();
	}
	
	
	
	public Optional<Vendor> getVendorById(Long id){
		return vendorRepository.findById(id);
	}
	
	
	public Vendor addVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	
	
	public Vendor updateVendor(Vendor v) {
		return vendorRepository.save(v);
	}
	
	
	public void deleteById(long id) {
		 vendorRepository.deleteById(id);
	}

	public Vendor findById(Long id) {
		return null;
		
	}

}
