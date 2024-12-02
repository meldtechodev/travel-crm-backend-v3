package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.security.services.RoleService;

@RestController
@RequestMapping("Motherson/crm/v1/role")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleCotroller {
     
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/create")
	public Role createRoleCont(@RequestBody Role role)
	{
		Role saverole=roleService.createRole(role);
		return saverole;
		
	}
	
	@GetMapping("/getall")
	public List<Role> getallrole(){
		
		   List<Role>getroleco=     roleService.getallroleser();
		return getroleco;
		
	}
 
}
