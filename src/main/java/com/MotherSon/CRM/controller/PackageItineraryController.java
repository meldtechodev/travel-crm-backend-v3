package com.MotherSon.CRM.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.MotherSon.CRM.models.PackageItinerary;
import com.MotherSon.CRM.repository.PkgRepository;
import com.MotherSon.CRM.security.services.PackageItineraryService;

import jakarta.validation.Valid;

@RestController
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("Motherson/crm/v1/packageitinerary")
	public class PackageItineraryController {
		
		
		@Autowired
		private PackageItineraryService packageItineraryService;
		
		@Autowired
		private PkgRepository pkgrepository;
		
		
//		@GetMapping("/getAll")
//		public List<PackageItinerary> getAllPackageItinerary(){
//			return packageItineraryService.getAllPackageItinerary();
//		}
		
		
//		@GetMapping("/getAll")
//		public Page<PackageItinerary> getPackageItinerary(
//				@RequestParam(value = "page" , defaultValue = "0") int page,
//				@RequestParam(value = "size" , defaultValue = "10") int size,
//				@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
//				){
//			return packageItineraryService.getPackageItinerary(page, size, sortDirection);
//		}
		
		@GetMapping("/getAll")
	    public List<PackageItinerary> getAllPackageItineraries(
	            @RequestParam(required = false) Long packageId,
	            @RequestParam(required = false) Long packageItineraryId) {
	        return packageItineraryService.getAllPackageItineraries(packageId, packageItineraryId);
	    }

		
		
		@GetMapping("/getById/{id}")
		public ResponseEntity<PackageItinerary> getPackageItineraryById(@PathVariable Long id){
			Optional<PackageItinerary> pi = packageItineraryService.getPackageItineraryById(id);
			return pi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}
		
		
//		public  ResponseEntity<?> addPackageItinerary ( @Valid @RequestBody PackageItinerary packageItinerary,BindingResult result) {
//			
//			if (result.hasErrors()) {
//		        Map<String, String> errors = new HashMap<>();
//		        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//		        System.out.print("Not corrextsssssssssssss");
//		        return ResponseEntity.badRequest().body(errors);
//		    }
//			    PackageItinerary packagetitinersave = packageItineraryService.addPackageItinerary(packageItinerary);
//			   
//			return new ResponseEntity<>("itinerary is created",HttpStatus.CREATED);
//			
//		}
		
		@PostMapping("/create")
		public  PackageItinerary addPackageItinerary (  @RequestBody PackageItinerary packageItinerary) {
			
			if(packageItinerary.getPackid()==null || !pkgrepository.existsById(packageItinerary.getPackid())){
				 throw new IllegalArgumentException("packid does not exist");
			
			}
			PackageItinerary packagetitinersave = packageItineraryService.addPackageItinerary(packageItinerary);
			   
//			return new ResponseEntity<>("itinerary is created",HttpStatus.CREATED);
			return packagetitinersave;
		}
		
		
//		@PostMapping("/create")
//		public PackageItinerary addPackageItinerary(@RequestBody PackageItinerary packageItinerary) {
//
//		    // Ensure that the packid is valid and exists in the repository
//		    if (packageItinerary.getPackid() == null || !pkgrepository.existsById(packageItinerary.getPackid().getId())) {
//		        throw new IllegalArgumentException("packid does not exist");
//		    }
//
//		    // Call the service to save the package itinerary
//		    PackageItinerary packagetitinersave = packageItineraryService.addPackageItinerary(packageItinerary);
//
//		    return packagetitinersave;
//		}

		
		@PutMapping("/{id}")
		public ResponseEntity<PackageItinerary> updatePackageItinerary(@PathVariable long id , @RequestBody PackageItinerary packageItinerary ){
			if(packageItinerary != null)
			{
				PackageItinerary pi = new PackageItinerary();
				pi.setId(id);
				pi.setDaynumber(packageItinerary.getDaynumber());
				pi.setCityname(packageItinerary.getCityname());
				pi.setDaytitle(packageItinerary.getDaytitle());
				pi.setProgram(packageItinerary.getProgram());
				pi.setMeals(packageItinerary.getMeals());
			//	pi.setTransportid(packageItinerary.getTransportid());
				pi.setCreatedby(packageItinerary.getCreatedby());
				pi.setModifiedby(packageItinerary.getModifiedby());
				pi.setIpaddress(packageItinerary.getIpaddress());
				pi.setStatus(packageItinerary.isStatus());
				pi.setIsdelete(packageItinerary.isIsdelete());
				pi.setCreateddate(packageItinerary.getCreateddate());
				pi.setModifieddate(packageItinerary.getModifieddate());
			
				
				packageItineraryService.updatePackageItinerary(pi);
				return ResponseEntity.ok(pi);
				
			}
			else
			{
				return ResponseEntity.notFound().build();
				
			}}
		
		
		@DeleteMapping("/{id}")
		public ResponseEntity<PackageItinerary> deletePackageItinerary(@PathVariable long id){
			
			try
			{
				packageItineraryService.findById(id);
				packageItineraryService.deletedById(id);
			return ResponseEntity.noContent().build();
			}
			finally
			{
				return ResponseEntity.notFound().build();
			}
		
	}}


