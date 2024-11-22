package com.MotherSon.CRM.security.services;
 
 
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
 
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.repository.DesignationsRepository;
 
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
 
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
	
	
 
 
	
//	public List<Designations> getAllDesignations(){
//		return designationsRepository.findAll();
//	}
	
	
	public Page<Designations> getDesignations(int page, int size, String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("designationName"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("designationName"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		
		return designationsRepository.findAll(pageable);
		
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
 
    @Transactional
    public Designations updateDesignation(Long id, Designations updatedDesignation) {
        Optional<Designations> existingDesignationOpt = designationsRepository.findById(id);
        if (existingDesignationOpt.isPresent()) {
            Designations existingDesignation = existingDesignationOpt.get();
            
            // Ensure the department exists
            Departments department = departmentsService.getDepartmentById(updatedDesignation.getDepartments().getId());
            if (department == null) {
                // Department doesn't exist
                throw new EntityNotFoundException("Department with ID " + updatedDesignation.getDepartments().getId() + " not found.");
            }
 
            // Set the valid department
            existingDesignation.setDepartments(department);
            existingDesignation.setDesignationName(updatedDesignation.getDesignationName());
            existingDesignation.setStatus(updatedDesignation.isStatus());
            existingDesignation.setModifiedBy(updatedDesignation.getModifiedBy());
            existingDesignation.setIpAddress(updatedDesignation.getIpAddress());
            existingDesignation.setIsdelete(updatedDesignation.isIsdelete());
            
            // Save the updated entity
            return designationsRepository.save(existingDesignation);
        }
        return null;
    }
	
 
 
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
 
 