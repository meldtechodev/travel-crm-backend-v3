package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
//	boolean existsByEmailId(String emailId);
//	
//	
//	boolean existsByContactNo(String contactNo);
//	
//	
//	boolean existsByAdharNo(String adharNo);
//	
//	
//	boolean existsByPassportId(String passportId);
//	
	
	
	boolean existsByEmailId(String emailId);
    boolean existsByContactNo(String contactNo);
    boolean existsByAdharNo(String adharNo);
    boolean existsByPassportId(String passportId);
    
    Customer findByContactNo(String contactNo);    
    
    //Customer findByEmailId(String emailId);
	Customer findByEmailId(String emailId);
	
	@Query("SELECT COUNT(*) FROM Customer")
    long countTotalCustomer();
 
    // Query to count active bookings where status = true (active bookings)
    @Query("SELECT COUNT(b) FROM Customer b WHERE b.status = true")
    long countActiveCustomer();
    
    long countByUser_UserId(Long userId);
    
    
    long countByUser_UserIdAndStatusTrue(Long userId);
 
    
    long countByUser_UserIdAndIsdeleteFalse(Long userId);
	Long countByStatusTrue();
 
 
	
 

}