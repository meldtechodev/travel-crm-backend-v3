package com.MotherSon.CRM.config;


import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Configuration
public class RoleInitializer {
     
	
	@Transactional
	@Bean
	CommandLineRunner createSuperAdminRole(RoleRepository roleRepository) {
	    return args -> {
	        System.out.println("CommandLineRunner is executing...");
	        
	        // Check if "Super Admin" role already exists in the database
	        Optional<Role> existingRole = roleRepository.findByRoleName("Super Admin");
	        if (!existingRole.isPresent()) {
	            Role superAdminRole = new Role();
	            superAdminRole.setRoleName("Super Admin");
	            superAdminRole.setStatus(true);
	            roleRepository.save(superAdminRole); // Save the role to the database
	            System.out.println("Super Admin role created successfully.");
	        } else {
	            System.out.println("Super Admin role already exists.");
	        }
	    };
	}
}
