package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Departments;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
	
	boolean existsByDepartmentName(String departmentName);

}
