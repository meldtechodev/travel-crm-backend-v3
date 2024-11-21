package com.MotherSon.CRM.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.security.services.DesignationsService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/designations")
public class DesignationsController {
	
	
	@Autowired
	private DesignationsService designationsService;
	
	
	
	@GetMapping("/getall")
	public List<Designations> getAllDesignations(){
		return designationsService.getAllDesignations();
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Designations> getDesignationsById(@PathVariable Long id)
	{
		Optional<Designations> designation = designationsService.getDesignationsById(id);
		return designation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
//	@PostMapping("/create")
//	public Designations addDesignations(@RequestBody Designations designations) {
//		Designations design = designationsService.addDesignations(designations);
//		return design;
//	}
	
	
	@PostMapping("/create")
    public ResponseEntity<?> addDesignations(@RequestBody Designations designations) {
        try {
        	Designations desig = designationsService.addDesignations(designations);
            return new ResponseEntity<>(desig, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage()); // Return the error message if department name already exists
        }}
	
//	@PutMapping("/updateby/{id}")
//	public ResponseEntity<Designations> updateDesignations(@PathVariable Long id, @RequestBody Designations designations){
//		if(designations != null)
//		{
//			Designations des = new Designations();
//			
//			des.setId(id);
//			des.setDesignationName(designations.getDesignationName());
//			des.setCreatedBy(designations.getCreatedBy());
//			des.setModifiedBy(designations.getModifiedBy());
//			des.setIpAddress(designations.getIpAddress());
//			des.setStatus(designations.isStatus());
//			des.setIsdelete(designations.isIsdelete());
//			des.setModifiedDate(designations.getModifiedDate());
//			des.setCreatedDate(designations.getCreatedDate());
//			
//			
//			
//			designationsService.updateDesignations(des);
//			return ResponseEntity.ok(des);
//		}
//		else
//		{
//			return ResponseEntity.notFound().build();
//		}
//	}
	
	
	
	@PutMapping("/updateby/{id}")
    public ResponseEntity<Designations> updateDesignation(@PathVariable Long id, @RequestBody Designations designations) {
        try {
            // Call service to update the Designation
            Designations updatedDesignation = designationsService.updateDesignation(id, designations);
            
            // Return the updated Designation with status 200 OK
            return ResponseEntity.ok(updatedDesignation);
        } catch (Exception e) {
            // Return an error message if the Designation is not found
            return ResponseEntity.notFound().build();
        }
    }
	
	
	
//	@DeleteMapping("/deletebyid/{id}")
//	public ResponseEntity<Designations> deleteDesignations(@PathVariable Long id)
//	{
//		
//		try
//		{
//		
//			designationsService.findById(id);
//			designationsService.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	finally
//	{
//		return ResponseEntity.notFound().build();
//	}
// 
//}}


	@DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
        	designationsService.deleteById(id);
            return ResponseEntity.ok("Company with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }}

    
	

