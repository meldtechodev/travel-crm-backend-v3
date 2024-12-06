package com.MotherSon.CRM.repository;
 
 
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.MotherSon.CRM.models.Departments;
 
@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
	
	boolean existsByDepartmentName(String departmentName);
	Page<Departments> findAll(Pageable pageable);
	
	Optional<Departments>findByDepartmentName(String departmentName);
 
}
 
 