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
	long count();
	long countByLeadStatusTrue();
	long countByLeadSourceIsNotNull();
	long countByUseridUserId(Long userId);
	long countByUseridUserIdAndLeadStatusTrue(Long userId);
	 
	 
	 
	 
	 
	long countByUseridUserIdAndLeadSourceIsNotNull(@Param("userId") Long userId);
	 
	@Query("SELECT qb.leadSource, COUNT(qb) FROM QueryBook qb GROUP BY qb.leadSource")
	List<Object[]> findLeadSourceCounts();
	 
	@Query("SELECT qb.leadSource, COUNT(qb) FROM QueryBook qb WHERE qb.userid.userId = :userId GROUP BY qb.leadSource")
	List<Object[]> findLeadSourceCountsForUser(@Param("userId") Long userId);
	
	@Query("SELECT q.destination.id, COUNT(q) FROM QueryBook q WHERE q.userid = :userId GROUP BY q.destination.id")
    List<Object[]> countQueryBooksByDestination(@Param("userId") User userId);
    
    @Query("SELECT q.packid, COUNT(q.packid) FROM QueryBook q " +
 	       "GROUP BY q.packid ORDER BY COUNT(q.packid) DESC")
 	List<Object[]> findTopFivePackages();
 	
 	@Query("SELECT q.destination.id AS destinationId, COUNT(q) AS queryCount " +
            "FROM QueryBook q " +
            "GROUP BY q.destination.id " +
            "ORDER BY queryCount DESC")
    List<Object[]> findTopDestinations(Pageable pageable);
    
    List<QueryBook> findByCustomerId(Long customerId);
    
    
    @Query("SELECT d.id, COUNT(q) FROM QueryBook q INNER JOIN q.destination d GROUP BY d.id ORDER BY COUNT(q) DESC")
    List<Object[]> findTopTenDestinationForSuperAdmin();
     
    @Query("SELECT d.id, COUNT(d) FROM QueryBook q INNER JOIN q.destination d WHERE q.userid.userId = :userId GROUP BY d.id ORDER BY COUNT(d) DESC")
    List<Object[]> findTopTenDestinationsForUser(@Param("userId") Long userId);
     
    @Query("SELECT qb.packid, COUNT(qb) FROM QueryBook qb GROUP BY qb.packid ORDER BY COUNT(qb) DESC")
    List<Object[]> findTopFivePackagesForSuperAdmin();
     
    @Query("SELECT qb.packid, COUNT(qb) FROM QueryBook qb WHERE qb.userid.userId = :userId GROUP BY qb.packid ORDER BY COUNT(qb) DESC")
    List<Object[]> findTopFivePackagesForUser(@Param("userId") Long userId);
    
    
    
    @Query("SELECT q.destination.destinationName, COUNT(q) " +
            "FROM QueryBook q " +
            "INNER JOIN q.destination d " +
            "WHERE q.userid.id = :userId " +  // Filter by user ID
            "GROUP BY q.destination.destinationName " +  // Group by destination name
            "ORDER BY COUNT(q) DESC")  // Order by the count of occurrences
    List<Object[]> findTopDestinationsByUser(@Param("userId") Long userId);
     
     
    @Query("SELECT q.destination.destinationName, COUNT(q) " +
    	       "FROM QueryBook q " +
    	       "INNER JOIN q.destination d " +
    	       "GROUP BY q.destination.destinationName " +
    	       "ORDER BY COUNT(q) DESC")
    	List<Object[]> findTopDestinationsForSuperAdmin();
    	
    	
    	@Query("SELECT q.leadSource, COUNT(q.leadSource) FROM QueryBook q WHERE q.userid.userId = :userId GROUP BY q.leadSource")
        List<Object[]> findLeadSourcesForUser(@Param("userId") Long userId);
     
        
        @Query("SELECT q.leadSource, COUNT(q.leadSource) FROM QueryBook q GROUP BY q.leadSource")
        List<Object[]> findLeadSourcesForSuperAdmin();
        
        
        @Query("SELECT u.name, s.stateName, c.countryName, COUNT(q) " +
 	           "FROM QueryBook q " +
 	           "JOIN q.userid u " +  // Join with User entity
 	           "JOIN q.destination d " + // Join with Destination entity
 	           "JOIN d.state s " +    // Join with State entity
 	           "JOIN s.country c " +  // Join with Country entity
 	           "WHERE q.isdelete = false " +  // Optionally filter out deleted entries
 	           "GROUP BY u.userId, s.id, c.id " +  // Group by userId, stateId, and countryId
 	           "ORDER BY u.userId ASC, COUNT(q) DESC")  // Order by userId and count in descending order
 	    List<Object[]> getTopStatesForUsers();
  
 		List<QueryBook> findByQueryType(String queryType);
      
}
