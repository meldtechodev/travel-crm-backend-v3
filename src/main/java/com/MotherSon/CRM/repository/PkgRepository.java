package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Pkg;

@Repository
public interface PkgRepository extends JpaRepository<Pkg, Long> {

	boolean existsById(Pkg packid);

	//boolean existsById(Pkg packid);
	//List<Pkg> findById(Long id);
	
	@Query("SELECT v.vendorName, p.pkName, d.destinationName, p.packageType " +
	           "FROM Pkg p " +
	           "LEFT JOIN Destination d ON p.toCityId = d.id " +
	           "JOIN Vendor v ON v.id = p.SupplierId " +  // Joining Vendor by SupplierId
	           "WHERE p.status = true")
	    List<Object[]> getPackageDetailsWithVendorAndDestination();

}
