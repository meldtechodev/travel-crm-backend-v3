package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.DesignationPermissions;

@Repository
public interface DesignationPermissionsRepository extends JpaRepository<DesignationPermissions,Long>{

}
