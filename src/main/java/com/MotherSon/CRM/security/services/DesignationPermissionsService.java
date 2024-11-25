package com.MotherSon.CRM.security.services;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
import com.MotherSon.CRM.models.DesignationPermissions;
 
import com.MotherSon.CRM.repository.DesignationPermissionsRepository;
 
import jakarta.persistence.EntityNotFoundException;
 
 
@Service
public class DesignationPermissionsService {
	
	
	@Autowired
	private DesignationPermissionsRepository designationPermissionsRepository;
	
	
	@Autowired
	private DesignationsService designationsService;
	
	
	
	public List<DesignationPermissions>  getAllDesignationPermissions()
	{
		List<DesignationPermissions> allPerm = designationPermissionsRepository.findAll();
		return allPerm;
	}
	
	
	
	
	public Optional<DesignationPermissions> getDesignationPermissionsById(Long id)
	{
		Optional<DesignationPermissions> dPermission = designationPermissionsRepository.findById(id);
		return dPermission;
	}
	
	
	
	
	public DesignationPermissions addDesignationPermissions(DesignationPermissions designationPermissions)
	{
		DesignationPermissions dePermissions = designationPermissionsRepository.save(designationPermissions);
		return dePermissions;
	}
	
	
	
	
	public void deleteById(Long id)
	{
		Optional<DesignationPermissions> delPerm = designationPermissionsRepository.findById(id);
		
		if(delPerm.isPresent()) {
			designationPermissionsRepository.deleteById(id);
		}
		else {
			throw new EntityNotFoundException(" This Id "  + id +  "     Is not Present in this Record  ");
		}
	}
	
	
	
	
	public DesignationPermissions updateDesignationPermissions(Long id, DesignationPermissions updatedDesignationPermissions) {
        // Check if the record exists
        if (designationPermissionsRepository.existsById(id)) {
            updatedDesignationPermissions.setId(id);  // Preserve the existing ID
            return designationPermissionsRepository.save(updatedDesignationPermissions);  // Save and return the updated record
        }
        return null;  // Return null if the record doesn't exist
    }}
	
	
 
	
 
 
 
 