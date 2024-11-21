package com.MotherSon.CRM.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.repository.RoleRepository;

@Service
public class RoleService {
   @Autowired
   
   private RoleRepository roleRepository;
   
   public Role createRole(Role role)
   {
	   
	   if(roleRepository.findByRoleName(role.getRoleName()).isPresent())
	   {
           throw new RuntimeException("Role '" + role.getRoleName() + "' already exists.");

	   }
	   
	return roleRepository.save(role);
	   
   }
}
