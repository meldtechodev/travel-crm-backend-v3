package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Pkg;

@Repository
public interface PkgRepository extends JpaRepository<Pkg, Long> {

	boolean existsById(Pkg packid);

	//boolean existsById(Pkg packid);
	//List<Pkg> findById(Long id);
}
