package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.DesignationModules;
import com.MotherSon.CRM.models.DesignationPermissions;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.models.Permissions;
import com.MotherSon.CRM.security.services.DesignationModulesService;
import com.MotherSon.CRM.security.services.DesignationsService;
import com.MotherSon.CRM.security.services.ModulesService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/designationModules")
public class DesignationModulesController {
	
	
	@Autowired
	private DesignationModulesService designationModulesService;
	
	@Autowired
	private DesignationsService designationsService;
	
	@Autowired
	private ModulesService modulesService;
	
	
	@GetMapping("/getall")
	public List<DesignationModules> getAllDesignationModules()
	{
		List<DesignationModules> dPer = designationModulesService.getAllDesignationModules();
		return dPer;
	}
	
	
	@GetMapping("/getbyid/{id}")
	public Optional<DesignationModules> getDesignationModulesById(@PathVariable Long id)
	{
		Optional<DesignationModules> getModu = designationModulesService.getDesignationModulesById(id);
		return getModu;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<String> addDesignationModules(@RequestBody DesignationModules designationModules)
	{
		DesignationModules dModules = designationModulesService.addDesignationModules(designationModules);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(" DesignationModules Successfully created");
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDesignationModules(
	        @PathVariable("id") Long id,
	        @RequestBody DesignationModules updatedDesignationModules) {
	 
	    // Ensure the designation exists in the database
	    Designations designation = designationsService.getDesignationById(updatedDesignationModules.getDesignations().getId());
	    if (designation == null) {
	        return ResponseEntity.badRequest().body("Designation with the provided ID does not exist.");
	    }
	 
	    // Ensure the permission exists in the database
	    Modules module = modulesService.getModuleById(updatedDesignationModules.getModules().getId());
	    if (module == null) {
	        return ResponseEntity.badRequest().body("Permission with the provided ID does not exist.");
	    }
	 
	    // Set the updated entities
	    updatedDesignationModules.setDesignations(designation);
	    updatedDesignationModules.setModules(module);
	 
	    // Update the DesignationPermissions record
	    DesignationModules updated = designationModulesService.updateDesignationModules(id, updatedDesignationModules);
	 
	    if (updated != null) {
	        return ResponseEntity.ok("Update successful! DesignationModules with ID " + id + " has been updated.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No DesignationModules found with ID " + id + ".");
	    }
	}
	
	
	
	

}
