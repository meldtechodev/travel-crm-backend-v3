package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.DesignationPermissions;
import com.MotherSon.CRM.security.services.DesignationPermissionsService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("Motherson/crm/v1/designationPermission")
public class DesignationPermissionsController {

@Autowired
private DesignationPermissionsService designationPermissionsService;



@GetMapping("/getall")
public List<DesignationPermissions> getAllDesignationPermissions()
{
	List<DesignationPermissions> dPer = designationPermissionsService.getAllDesignationPermissions();
	return dPer;
}


@GetMapping("/getbyid/{id}")
public Optional<DesignationPermissions> getDesignationPermissionsById(@PathVariable Long id)
{
	Optional<DesignationPermissions> getPer = designationPermissionsService.getDesignationPermissionsById(id);
	return getPer;
}




@PostMapping("/create")
public ResponseEntity<String> addDesignationPermissions(@RequestBody DesignationPermissions designationPermissions)
{
	DesignationPermissions dPermission = designationPermissionsService.addDesignationPermissions(designationPermissions);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(" DesignationPermissions Successfully created");
}


@DeleteMapping("/deleteby/{id}")
public ResponseEntity<String> deleteDesignationPermissionsById(@PathVariable Long id)
{
	try
	{
	designationPermissionsService.deleteById(id);
	return ResponseEntity.ok("DesignationPermissions with This ID " + id + " Deleted successfully ");
    
}catch(EntityNotFoundException e) {
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());


}
}

}


