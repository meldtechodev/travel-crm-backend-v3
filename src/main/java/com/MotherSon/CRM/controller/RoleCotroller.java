package com.MotherSon.CRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.security.services.RoleService;

@RestController
@RequestMapping("Motherson/crm/v1/role")

public class RoleCotroller {
     
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/create")
	public Role createRoleCont(@RequestBody Role role)
	{
		Role saverole=roleService.createRole(role);
		return saverole;
		
	}
}
