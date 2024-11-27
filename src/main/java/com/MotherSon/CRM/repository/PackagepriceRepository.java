package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.PackagePrice;

@Repository
public interface PackagepriceRepository extends JpaRepository<PackagePrice,Long> {

	boolean existsByPackid(Long packid);

} 

