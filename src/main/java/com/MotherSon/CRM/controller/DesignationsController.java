package com.MotherSon.CRM.controller;
 
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
 
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.security.services.DepartmentsService;
import com.MotherSon.CRM.security.services.DesignationsService;
 
import jakarta.persistence.EntityNotFoundException;
 
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/designations")
public class DesignationsController {
	
	
	@Autowired
	private DesignationsService designationsService;
	
	
	@Autowired
	private DepartmentsService departmentsService;
	
	
	
	@GetMapping("/getall")
	public Page<Designations> getDesignations(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue =  "asc") String sortDirection
			){
		return designationsService.getDesignations(page, size, sortDirection);
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
	
 
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Designations> updateDesignation(
	        @PathVariable("id") Long id,
	        @RequestBody Designations updatedDesignation) {
 
	    // Ensure the department exists in the database
	    Departments department = departmentsService.getDepartmentById(updatedDesignation.getDepartments().getId());
	    if (department == null) {
	        return ResponseEntity.badRequest().body(null); // or throw an exception
	    }
 
	    updatedDesignation.setDepartments(department);
	    Designations updated = designationsService.updateDesignation(id, updatedDesignation);
 
	    if (updated != null) {
	        return ResponseEntity.ok(updated);
	    } else {
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
 
    
	
 
 
 