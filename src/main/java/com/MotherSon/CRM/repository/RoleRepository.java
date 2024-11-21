package com.MotherSon.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Role;
 @Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    
	 Optional<Role> findByRoleName(String roleName);
	  
}
