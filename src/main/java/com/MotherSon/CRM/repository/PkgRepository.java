package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Pkg;

@Repository
public interface PkgRepository extends JpaRepository<Pkg, Long> {
}
