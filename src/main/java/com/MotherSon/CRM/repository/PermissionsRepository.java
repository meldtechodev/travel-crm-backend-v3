package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MotherSon.CRM.models.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long>  {
	
	boolean existsByPermissionName(String permissionName);

}
