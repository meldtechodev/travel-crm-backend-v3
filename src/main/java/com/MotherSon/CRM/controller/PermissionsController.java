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

import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.models.Permissions;
import com.MotherSon.CRM.security.services.ModulesService;
import com.MotherSon.CRM.security.services.PermissionsService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/permissions")
public class PermissionsController {
	
	
	@Autowired
	private PermissionsService permissionsService;
	
	@Autowired
    private ModulesService modulesService;
 
	
	
//	@GetMapping("/getbyid/{id}")
//	public ResponseEntity<Permissions> getPermissionsById(@PathVariable Long id)
//	{
//		Optional<Permissions> permission = permissionsService.getPermissionsById(id);
//		return permission.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}
	
	
	
	@GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getPermissionsById(@PathVariable Long id) {
        // Fetch the Permissions entity by ID from the service
        Optional<Permissions> permission = permissionsService.getPermissionsById(id);

        if (permission.isPresent()) {
            // Return the found Permissions object with 200 OK status
            return new ResponseEntity<>(permission.get(), HttpStatus.OK);
        } else {
            // Return 404 NOT FOUND with a custom message if the ID is not found
            return new ResponseEntity<>("ID " + id + " not present in the database.", HttpStatus.NOT_FOUND);
        }
    }
	
	
	
	@GetMapping("/getall")
	public List<Permissions> getAllPermissions(){
		List<Permissions> getallpermission = permissionsService.getAllPermissions();
		return getallpermission;
	}
	
	
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> addPermissions(@RequestBody Permissions permissions){
		
		if (permissionsService.existsByPermissionName(permissions.getPermissionName())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Permissions with the PermissionName " + permissions.getPermissionName() + " already exists.");
	    }
		
		Permissions per = permissionsService.addPermissions(permissions);
		return ResponseEntity.status(HttpStatus.CREATED).body(" Permission is created successfully");
		
	}
	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<String> delettePermissionsById(@PathVariable Long id){
		try
		{
			permissionsService.deleteById(id);
			return ResponseEntity.ok("Permissions with this ID" + id + " Deleted Successfully. ");
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<String> updatePermissions(
            @PathVariable("id") Long id,
            @RequestBody Permissions updatedPermissions) {
 
        
        if (updatedPermissions.getModules() != null) {
            Modules module = modulesService.getModuleById(updatedPermissions.getModules().getId());
            if (module == null) {
                return ResponseEntity.badRequest().body("Module with the provided ID does not exist.");
            }
            updatedPermissions.setModules(module);
        }
 
        // Ensure the Permissions entity exists
        Permissions existingPermissions = permissionsService.updatePermissions(id, updatedPermissions);
 
        if (existingPermissions != null) {
            return ResponseEntity.ok("Update successful! Permissions with ID " + id + " has been updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Permissions found with ID " + id + ".");
        }
    }
 

}
