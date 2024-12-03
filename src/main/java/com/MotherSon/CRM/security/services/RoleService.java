package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Policy;
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
   
   
//   public List<Role> getallroleser()
//   {
//	 List<Role>getrolse=  roleRepository.findAll();
//	        return getrolse;
//   }
   
   
   
   public Page<Role> getRole(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("roleName"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort =  Sort.by(Sort.Order.desc("roleName"));
		}
		
		PageRequest  pageable = PageRequest.of(page, size, sort);
		return roleRepository.findAll(pageable);
		}
   
   
}
