package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.DesignationModules;
import com.MotherSon.CRM.repository.DesignationModulesRepository;

@Service
public class DesignationModulesService {
	
	
	@Autowired
	private DesignationModulesRepository designationModulesRepository;
	
	
	@Autowired
	private DesignationsService designationsService;
	
	
	
	public List<DesignationModules>  getAllDesignationModules()
	{
		List<DesignationModules> allPerm = designationModulesRepository.findAll();
		return allPerm;
	}


	
	public Optional<DesignationModules> getDesignationModulesById(Long id)
	{
		Optional<DesignationModules> dModule = designationModulesRepository.findById(id);
		return dModule;
	}
	
	
	public DesignationModules addDesignationModules(DesignationModules designationModules)
	{
		DesignationModules deModules = designationModulesRepository.save(designationModules);
		return deModules;
	}

	
	
	public DesignationModules updateDesignationModules(Long id, DesignationModules updatedDesignationModules) {
        // Check if the record exists
        if (designationModulesRepository.existsById(id)) {
            updatedDesignationModules.setId(id);  // Preserve the existing ID
            return designationModulesRepository.save(updatedDesignationModules);  // Save and return the updated record
        }
        return null;  // Return null if the record doesn't exist
    }}
	

