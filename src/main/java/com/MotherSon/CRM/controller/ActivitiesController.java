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

import com.MotherSon.CRM.models.Activities;
import com.MotherSon.CRM.security.services.ActivitiesService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/activities")
public class ActivitiesController {
	
	
	@Autowired
	private ActivitiesService  activitiesService;
	
	
	@GetMapping("/getby/{id}")
	public ResponseEntity<Activities> getActivitiesById(@PathVariable Long id){
		Optional<Activities> activities = activitiesService.getActivitiesById(id);
		return activities.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@GetMapping("/getAll")
	public List<Activities> getAllActivities(){
		return activitiesService.getAllActivities();
	}
	
	
	@PostMapping("/create")
	public Activities addActivities(@RequestBody Activities activities) {
		return this.activitiesService.addActivities(activities);
	}
	
	
	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<Activities> updateActivities(@PathVariable Long id , @RequestBody Activities activities){
		if(activities != null)
		{
			Activities ai = new Activities();
			
			ai.setId(id);
			
			ai.setTitle(activities.getTitle());
			ai.setIpaddress(activities.getIpaddress());
			ai.setStatus(activities.isStatus());
			ai.setIsdelete(activities.isIsdelete());
			ai.setCreatedby(activities.getCreatedby());
			ai.setModifiedby(activities.getModifiedby());
			ai.setCreatedDate(activities.getCreatedDate());
			ai.setModifiedDate(activities.getModifiedDate());
			
		
		
			activitiesService.updateActivities(ai);
		return ResponseEntity.ok(ai);
	}

	else
	{
		return ResponseEntity.notFound().build();
	}
	}

}

