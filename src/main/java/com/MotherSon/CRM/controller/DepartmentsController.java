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
import com.MotherSon.CRM.security.services.DepartmentsService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/departments")
public class DepartmentsController {
	
	

	@Autowired
	private DepartmentsService departmentsService;
	
	
	
	@GetMapping("/getbyId/{id}")
	public ResponseEntity<Departments> getDepartmentsById(@PathVariable Long id){
		Optional<Departments> depart = departmentsService.getDepartmentsById(id);
		return depart.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	
	@GetMapping("/getAll")
	public List<Departments> getAllDepartments(){
		return departmentsService.getAllDepartments();
	}
	
	
	
//	@PostMapping("/create")
//	public Departments addDepartments(@RequestBody Departments departments)
//	{
//		
//		if (departmentsService.existsByDepartmentName(departments.getDepartmentName())) {
//	        return ResponseEntity.status(HttpStatus.CONFLICT)
//	                .body("Query with the ContactNo " + departments.getDepartmentName() + " already exists.");
//		
//		Departments depart = departmentsService.addDepartments(departments);
//		return depart;
//	}
	
	
	
	@PostMapping("/create")
    public ResponseEntity<?> addDepartments(@RequestBody Departments departments) {
        try {
            Departments depart = departmentsService.addDepartments(departments);
            return new ResponseEntity<>(depart, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage()); // Return the error message if department name already exists
        }
    }

	
	
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Departments> updateDepartments(@PathVariable Long id, @RequestBody Departments departments){
		if(departments != null)
		{
			Departments depart = new Departments();
			
			depart.setId(id);
			depart.setDepartmentName(departments.getDepartmentName());
			depart.setCreatedBy(departments.getCreatedBy());
			depart.setModifiedBy(departments.getModifiedBy());
			depart.setIpaddress(departments.getIpaddress());
			depart.setStatus(departments.isStatus());
			depart.setIsdelete(departments.isIsdelete());
			
			
			departmentsService.updateDepartments(depart);
			return ResponseEntity.ok(depart);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	
//	@DeleteMapping("/deletebyid/{id}")
//	public ResponseEntity<Departments> deleteDepartments(@PathVariable Long id)
//	{
//		
//		try
//		{
//		
//		departmentsService.findById(id);
//		departmentsService.deleteById(id);
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
    public ResponseEntity<String> deleteDepartments(@PathVariable Long id) {
        try {
        	departmentsService.deleteById(id);
            return ResponseEntity.ok("Departments with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }}
