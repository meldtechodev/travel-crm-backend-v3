package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.PackageTheme;

@Repository
public interface PackageThemeRepository extends JpaRepository<PackageTheme,Long> {

}