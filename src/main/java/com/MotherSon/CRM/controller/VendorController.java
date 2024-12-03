package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Transport;
import com.MotherSon.CRM.models.Vendor;
import com.MotherSon.CRM.security.services.VendorService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/vendor")
public class VendorController {
	
	
	@Autowired
	private VendorService vendorService;
	
	
	
//	@GetMapping("/getAll")
//	public List<Vendor> getAllVendor(){
//		return vendorService.getAllVendor();
//	}
	
	
	@GetMapping("/getAll")
	public Page<Vendor> getVendor(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return vendorService.getVendor(page , size , sortDirection);
	}
	
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable Long id){
		Optional<Vendor> vendor = vendorService.getVendorById(id);
		return vendor.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/create")
	public Vendor addVendor(@RequestBody Vendor vendor) {
		return this.vendorService.addVendor(vendor);
	}
	
	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<Vendor> updateVendor(@PathVariable Long id , @RequestBody Vendor vendor){
		if(vendor != null)
		{
			Vendor v = new Vendor();
			
			v.setId(id);
			v.setVendorName(vendor.getVendorName());
			v.setVendorEmail(vendor.getVendorEmail());
			v.setVendorContactNo(vendor.getVendorContactNo());
			v.setVendorAddress(vendor.getVendorAddress());
			v.setIpAddress(vendor.getIpAddress());
			v.setStatus(vendor.isStatus());
			v.setIsdelete(vendor.isIsdelete());
			v.setCreatedby(vendor.getCreatedby());
			v.setModifiedby(vendor.getModifiedby());
			v.setCreatedDate(vendor.getCreatedDate());
			v.setModifiedDate(vendor.getModifiedDate());
			
			vendorService.updateVendor(v);
			return ResponseEntity.ok(v);
		}

		else
		{
			return ResponseEntity.notFound().build();
		}
		}
	
	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<Vendor> deleteVendor(@PathVariable Long id){
		
		try
		{
			vendorService.findById(id);
			vendorService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
	}
	}
