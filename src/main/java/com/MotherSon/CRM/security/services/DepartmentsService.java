package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.repository.DepartmentsRepository;

import jakarta.persistence.EntityNotFoundException;



@Service
public class DepartmentsService {
	
	
@Autowired
	
	private DepartmentsRepository departmentsRepository;
	
	
	
	public Optional<Departments> getDepartmentsById(Long id){
		return departmentsRepository.findById(id);
	}
	
	
	public List<Departments> getAllDepartments(){
		return departmentsRepository.findAll();
	}
	
	
	
	public Departments addDepartments(Departments departments) {
        // Check if department name already exists
        if (departmentsRepository.existsByDepartmentName(departments.getDepartmentName())) {
            throw new IllegalArgumentException("Department with name " + departments.getDepartmentName() + " already exists.");
        }

        // Save the department if it doesn't exist
        return departmentsRepository.save(departments);
    }
	
	
	
//	public Departments addDepartments(Departments departments)
//	
//	{
//		
//		Departments department = departmentsRepository.save(departments);
//		return department;
//	}
	
	
//	public void deleteById(Long id)
//	{
//		departmentsRepository.deleteById(id);
//	}
//	
//	public Departments findById(Long id)
//	{
//	return null;
// 
//}
	
	
	 public Departments getDepartmentById(Long id) {
	        return departmentsRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
	    }
	
	
	
	public void deleteById(Long id) {
	    // Check if the company exists
	    Optional<Departments> existingDepartmentsOptional = departmentsRepository.findById(id);

	    if (existingDepartmentsOptional.isPresent()) {
	        // Delete the company if it exists
	    	departmentsRepository.deleteById(id);
	        
	    } else {
	        // Handle case where company is not found
	        throw new EntityNotFoundException("Departments with ID " + id + " not found.");
	    }
	}

	
	
	public Departments updateDepartments(Departments de) {
		return departmentsRepository.save(de);
	}
	

}