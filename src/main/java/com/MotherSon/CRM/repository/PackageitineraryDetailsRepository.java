package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.PackageitineraryDetails;

@Repository
public interface PackageitineraryDetailsRepository extends JpaRepository<PackageitineraryDetails,Long>{


}