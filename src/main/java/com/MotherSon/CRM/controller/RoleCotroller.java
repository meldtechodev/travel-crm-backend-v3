package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.PolicyDetails;
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
	
	
	
//	@GetMapping("/getall")
//	public List<Role> getallrole()
//	{
//		
//		   List<Role>getroleco=     roleService.getallroleser();
//		return getroleco;
//		
//	}
	
	
	@GetMapping("/getall")
	public Page<Role> getRole(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return roleService.getRole(page , size , sortDirection);
	}
	
}
