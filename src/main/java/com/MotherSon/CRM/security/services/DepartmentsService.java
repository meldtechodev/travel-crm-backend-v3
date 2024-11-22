package com.MotherSon.CRM.security.services;

import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.repository.DepartmentsRepository;
 
import jakarta.persistence.EntityNotFoundException;
 
 
 
@Service
public class DepartmentsService {
	
	
@Autowired
	
	private DepartmentsRepository departmentsRepository;
	
	
	
	public Optional<Departments> getDepartmentsById(Long id){
		return departmentsRepository.findById(id);
	}
	
	
//	public List<Departments> getAllDepartments(){
//		return departmentsRepository.findAll();
//	}
	
	
//	public Page<Departments> getDepartments(int page, int size){
//		PageRequest pageable = PageRequest.of(page, size);
//		
//		//PageRequest paging = PageRequest.of(page, size);
//		
//		return departmentsRepository.findAll(pageable);
//	}
	
	
	public Page<Departments> getDepartments(int page, int size, String sortDirection) {
        // Set default sorting direction to ascending
        Sort sort = Sort.by(Sort.Order.asc("departmentName"));
 
        // Change sorting direction based on the user input
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = Sort.by(Sort.Order.desc("departmentName"));
        }
 
        // Create a Pageable object with page, size, and sort
        PageRequest pageable = PageRequest.of(page, size, sort);
 
        // Return the paginated and sorted result
        return departmentsRepository.findAll(pageable);
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
 