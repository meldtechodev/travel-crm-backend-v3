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
 
 
import com.MotherSon.CRM.models.DesignationPermissions;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Permissions;
import com.MotherSon.CRM.security.services.DesignationPermissionsService;
import com.MotherSon.CRM.security.services.DesignationsService;
import com.MotherSon.CRM.security.services.PermissionsService;
 
import jakarta.persistence.EntityNotFoundException;
 
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/designationPermission")
public class DesignationPermissionsController {
 
@Autowired
private DesignationPermissionsService designationPermissionsService;
 
 
 
@Autowired
private DesignationsService designationsService;
 
@Autowired
private PermissionsService permissionsService;
 
 
 
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
 
 
//@PutMapping("/update/{id}")
//public ResponseEntity<DesignationPermissions> updateDesignationPermissions(
//        @PathVariable("id") Long id,
//        @RequestBody DesignationPermissions updatedDesignationPermissions) {
//
//    // Ensure the designation exists in the database
//    Designations designation = designationsService.getDesignationById(updatedDesignationPermissions.getDesignations().getId());
//    if (designation == null) {
//        return ResponseEntity.badRequest().body(null); // or throw an exception
//    }
//
//    // Ensure the permission exists in the database
//    Permissions permission = permissionsService.getPermissionById(updatedDesignationPermissions.getPermissions().getId());
//    if (permission == null) {
//        return ResponseEntity.badRequest().body(null); // or throw an exception
//    }
//
//    // Set the updated entities
//    updatedDesignationPermissions.setDesignations(designation);
//    updatedDesignationPermissions.setPermissions(permission);
//
//    // Update the DesignationPermissions record
//    DesignationPermissions updated = designationPermissionsService.updateDesignationPermissions(id, updatedDesignationPermissions);
//
//    if (updated != null) {
//        return ResponseEntity.ok(updated);
//    } else {
//        return ResponseEntity.notFound().build();
//    }
//}
 
	
 
 
 
 
@PutMapping("/update/{id}")
public ResponseEntity<String> updateDesignationPermissions(
        @PathVariable("id") Long id,
        @RequestBody DesignationPermissions updatedDesignationPermissions) {
 
    // Ensure the designation exists in the database
    Designations designation = designationsService.getDesignationById(updatedDesignationPermissions.getDesignations().getId());
    if (designation == null) {
        return ResponseEntity.badRequest().body("Designation with the provided ID does not exist.");
    }
 
    // Ensure the permission exists in the database
    Permissions permission = permissionsService.getPermissionById(updatedDesignationPermissions.getPermissions().getId());
    if (permission == null) {
        return ResponseEntity.badRequest().body("Permission with the provided ID does not exist.");
    }
 
    // Set the updated entities
    updatedDesignationPermissions.setDesignations(designation);
    updatedDesignationPermissions.setPermissions(permission);
 
    // Update the DesignationPermissions record
    DesignationPermissions updated = designationPermissionsService.updateDesignationPermissions(id, updatedDesignationPermissions);
 
    if (updated != null) {
        return ResponseEntity.ok("Update successful! DesignationPermissions with ID " + id + " has been updated.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No DesignationPermissions found with ID " + id + ".");
    }
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
 
 
 
 