package com.MotherSon.CRM.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.QueryBook;
import com.MotherSon.CRM.models.User;

//import com.MotherSon.CRM.models.QueryBook;

@Repository
public interface QueryBookRepository extends JpaRepository<QueryBook, Long> {
	
//	boolean existsByEmailId(String emailId);
//
//	//boolean existsEmailId(String emailId); 
//	
//	boolean existsByContactNo(String contactNo);
	
	List<QueryBook> findByCustomerId(Long customerId);
	
	@Query("SELECT COUNT(DISTINCT b.userid) FROM QueryBook b")
    long countTotalUsers();

    // Query to count active users (based on active users in QueryBook)
    @Query("SELECT COUNT(DISTINCT b.userid) FROM QueryBook b WHERE b.userid.status = true")
    long countActiveUsers();

	List<QueryBook> findAll();

//	@Query("SELECT COUNT(b) FROM QueryBook b")
//    long countTotalQueryBook();
//
//    // Query to count active bookings where status = true (active bookings)
//    @Query("SELECT COUNT(b) FROM QueryBook b WHERE b.leadStatus = true")
//    long countActiveQueryBook();

	
	
	@Query("SELECT q.destination.id, COUNT(q) FROM QueryBook q WHERE q.userid = :userId GROUP BY q.destination.id")
    List<Object[]> countQueryBooksByDestination(@Param("userId") User userId);
    
    
    //Top Destination
    
    
    @Query("SELECT q.destination.id AS destinationId, COUNT(q) AS queryCount " +
            "FROM QueryBook q " +
            "GROUP BY q.destination.id " +
            "ORDER BY queryCount DESC")
     List<Object[]> findTopDestinationss(Pageable pageable);  // This code is correct
     
     
     
     // top destination by limit
     
     
     @Query("SELECT q.destination.id AS destinationId, COUNT(q) AS queryCount " +
             "FROM QueryBook q " +
             "GROUP BY q.destination.id " +
             "ORDER BY queryCount DESC")
     List<Object[]> findTopDestinations(Pageable pageable);
     
     
     
     @Query("SELECT q.leadSource, COUNT(q) FROM QueryBook q GROUP BY q.leadSource")
     List<Object[]> findLeadSourceCounts();
     
     
     //Topdestination by user id
     
     @Query("SELECT q.destination.id AS destinationId, COUNT(q) AS queryCount " +
             "FROM QueryBook q WHERE q.userid.id = :userId " +
             "GROUP BY q.destination.id ORDER BY queryCount DESC")
      List<Object[]> findTopQueriesByDestinationAndUserId(Long userId);
      
      
      @Query("SELECT COUNT(b) FROM QueryBook b")
      long countTotalQueryBook();
 
      // Query to count active bookings where status = true (active bookings)
      @Query("SELECT COUNT(b) FROM QueryBook b WHERE b.leadStatus = true")
      long countActiveQueryBook();
      
      @Query("SELECT q.packid, COUNT(q.packid) FROM QueryBook q " +
   	       "GROUP BY q.packid ORDER BY COUNT(q.packid) DESC")
   	List<Object[]> findTopFivePackages();
    
 
}
