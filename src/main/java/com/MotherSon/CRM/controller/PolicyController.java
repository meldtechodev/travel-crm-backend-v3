package com.MotherSon.CRM.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Policy;
import com.MotherSon.CRM.security.services.PolicyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/policy")
public class PolicyController {
	
	
	@Autowired
	 
	private  PolicyService policyservice;
	 
	 @PostMapping("/create")
	public ResponseEntity<?> createPolicy(@Valid @RequestBody Policy policy,BindingResult bindingResult)
	{
		 
		  if(bindingResult.hasErrors())
   	   {
   		   Map<String,String> errors = new HashMap<>();
   		   
   		    // Loop through each error and store the field and its message in the map
              bindingResult.getFieldErrors().forEach(error -> {
                  errors.put(error.getField(), error.getDefaultMessage());
              });
              
           // Return the map of errors with a BAD_REQUEST status
              return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
          }
        Policy policyser=  policyservice.createpolicy(policy);
        
		return new ResponseEntity<>("policy is created",HttpStatus.CREATED);
        
    }
	 
	 @GetMapping("/getpolicybyid/{id}")
	 
	public Optional<Policy> getallpolicybyid(@PathVariable Long id)
	{
		        Optional<Policy> policyserid=    policyservice.getpolicyById(id); 
		        
		     return policyserid;
		
	}
	 
	 
	 
	 @GetMapping("/getallpolicy")
	 public ResponseEntity<List<Policy>>getallpolicy()
	 {
		        List<Policy>getallpolicyser=  policyservice.getallpolicyser();
		return ResponseEntity.ok(getallpolicyser);
		 
	 }
	 
@PutMapping("/updatedby/{id}")
	 
	 public ResponseEntity<Policy> updatepolicy( @PathVariable Long id,@RequestBody Policy policyDet)
	 {
		 
		 
		   if(policyDet !=null)
		   {
			   Policy policy=new Policy();
			   policy.setId(id);
			   policy.setPolicyName(policyDet.getPolicyName());
		        policy.setPolicyDescription(policyDet.getPolicyDescription());
		        policy.setCreatedBy(policyDet.getCreatedBy());
		        policy.setModifiedby(policyDet.getModifiedby());
		        policy.setIpaddress(policyDet.getIpaddress());
		        policy.setStatus(policyDet.isStatus());
		        policy.setIsdelete(policyDet.isIsdelete());
		        policy.setCreatedDate(policyDet.getCreatedDate());
		        policy.setModifiedDate(policyDet.getModifiedDate());
		        policyservice.updatepolicy(id, policy);
		        
		          return ResponseEntity.ok(policy);
		   }
		  
		   else
		   {
			   return ResponseEntity.notFound().build();
		   }
		        
		          
		 
	 }
	 
	 @DeleteMapping("/delete/{id}")
	 public void deletebyid(@PathVariable Long id)
	 {
		   policyservice.Deletebyid(id);
	 }
	 
	 
}