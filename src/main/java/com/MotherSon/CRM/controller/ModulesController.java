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
 
import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.security.services.ModulesService;
 
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
 
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/modules")
public class ModulesController {
	
	
	@Autowired
	private ModulesService modulesService;
	
	
	@GetMapping("/getall")
	public List<Modules> getAllModules(){
		
		List<Modules> getmodule = modulesService.getAllModules();
		return getmodule;
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getModulesById(@PathVariable Long id){
		Optional<Modules> existingmodule = modulesService.getModulesById(id);
		
		if(existingmodule.isPresent()) {
			return new ResponseEntity<>(existingmodule.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(" ID " + id + "Not present in the Database", HttpStatus.NOT_FOUND);
	
		}
		
	}
	
	
//	@PostMapping("/create")
//	public ResponseEntity<String> addModules(@RequestBody Modules modules){
//		Modules admodule = modulesService.addModules(modules);
//		return ResponseEntity.status(HttpStatus.CREATED).body(" Modules is Created ");
//	}
	
	@PostMapping("/create")
	public ResponseEntity<?> addModules(@RequestBody Modules modules){
		try
		{
			Modules modu = modulesService.addModules(modules);
			return new ResponseEntity<>(modu, HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		
		}
	}
	
	
	
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Object> updateModule(@PathVariable Long id, @Valid @RequestBody Modules updatedModule) {
        // Call the service to update the module
        Modules updated = modulesService.updateModule(id, updatedModule);
        
        // If the module was not found, return a NOT_FOUND response
        if (updated == null) {
            return new ResponseEntity<>("Module with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        
        // Return the updated module in the response with OK status
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
 
 
 
 
@DeleteMapping("/deleteby/{id}")
public ResponseEntity<String> deleteModulesById(@PathVariable Long id){
	try
	{
		modulesService.deleteById(id);
		return ResponseEntity.ok(" Modules with this ID" + id + " Delete Successfully");
	}
	catch(EntityNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
	
}
 
 
 
 
 
 
 
 
 
 
 