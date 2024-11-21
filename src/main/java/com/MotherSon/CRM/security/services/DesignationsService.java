package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.repository.DesignationsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DesignationsService {
	
	
	@Autowired
	private DesignationsRepository designationsRepository;
	
	
	public Designations addDesignations(Designations designations) {
        // Check if department name already exists
        if (designationsRepository.existsByDesignationName(designations.getDesignationName())) {
            throw new IllegalArgumentException("Department with name " + designations.getDesignationName() + " already exists.");
        }

        // Save the department if it doesn't exist
        return designationsRepository.save(designations);
    }
	
	


	
	public List<Designations> getAllDesignations(){
		return designationsRepository.findAll();
	}
	
	public Optional<Designations> getDesignationsById(Long id){
		return designationsRepository.findById(id);
	}


//	public Designations updateDesignations(Designations de) {
//		return designationsRepository.save(de);
//	}
	
	
	
	@Autowired
    private DepartmentsService departmentsService;

    // Method to get a Designation by ID
    public Designations getDesignationById(Long id) {
        // If the Designation exists, return it, else throw an exception
        Optional<Designations> designation = designationsRepository.findById(id);
        if (designation.isPresent()) {
            return designation.get();
        } else {
            throw new RuntimeException("Designation not found with ID: " + id);
        }
    }

    // Method to update the Designation
    public Designations updateDesignation(Long id, Designations updatedDesignation) {
        // Get the existing Designation by ID
        Designations existingDesignation = getDesignationById(id);

        // Update the fields of the existing Designation
        existingDesignation.setDesignationName(updatedDesignation.getDesignationName());
        existingDesignation.setCreatedBy(updatedDesignation.getCreatedBy());
        existingDesignation.setModifiedBy(updatedDesignation.getModifiedBy());
        existingDesignation.setIpAddress(updatedDesignation.getIpAddress());
        existingDesignation.setStatus(updatedDesignation.isStatus());
        existingDesignation.setIsdelete(updatedDesignation.isIsdelete());
        existingDesignation.setModifiedDate(updatedDesignation.getModifiedDate());
        existingDesignation.setDepartments(updatedDesignation.getDepartments());

        // If the department is provided, link the department to this designation
//        if (updatedDesignation.getDepartments() != null && updatedDesignation.getDepartments().getId() != null) {
//        	
//        	
//            existingDesignation.setDepartments(departmentsService.getDepartmentById(updatedDesignation.getDepartments().getId()));
//        }

        // Save the updated Designation entity
        return designationsRepository.save(existingDesignation);
    }

	
//	public void deleteById(Long id)
//	{
//		designationsRepository.deleteById(id);
//	}
//	
//	public Designations findById(Long id)
//	{
//	return null;
// 
//}
//	
//}

	public void deleteById(Long id) {
	    // Check if the company exists
	    Optional<Designations> existingDesignationsOptional = designationsRepository.findById(id);

	    if (existingDesignationsOptional.isPresent()) {
	        // Delete the company if it exists
	    	designationsRepository.deleteById(id);
	        
	    } else {
	        // Handle case where company is not found
	        throw new EntityNotFoundException("Designations with ID " + id + " not found.");
	    }
	}
}
