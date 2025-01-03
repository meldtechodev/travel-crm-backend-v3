package com.MotherSon.CRM.repository;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.MotherSon.CRM.models.User;
 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
	//boolean existsById(int i);
	
	boolean existsByEmail(String email);  // Check if email already exists
    Optional<User> findByEmail(String email);
    
    
    
    @Query("SELECT COUNT(b) FROM User b")
    long countTotalUser();
 
    // Query to count active bookings where status = true (active bookings)
    @Query("SELECT COUNT(b) FROM User b WHERE b.status = true")
    long countActiveUser();
    
long countByIsdeleteFalse();  // Count users where isdelete is false (active users)
    
    long countByStatusTrueAndIsdeleteFalse();  // Count active users (status = true and isdelete = false)
    
    
    @Query("SELECT qb.packid, COUNT(qb) FROM QueryBook qb GROUP BY qb.packid ORDER BY COUNT(qb) DESC")
    List<Object[]> findTopFivePackagesForSuperAdmin();
     
    @Query("SELECT qb.packid, COUNT(qb) FROM QueryBook qb WHERE qb.userid.userId = :userId GROUP BY qb.packid ORDER BY COUNT(qb) DESC")
    List<Object[]> findTopFivePackagesForUser(@Param("userId") Long userId);
     
     
     
    @Query("SELECT d.id, COUNT(q) FROM QueryBook q INNER JOIN q.destination d GROUP BY d.id ORDER BY COUNT(q) DESC")
    List<Object[]> findTopTenDestinationForSuperAdmin();
     
    @Query("SELECT d.id, COUNT(d) FROM QueryBook q INNER JOIN q.destination d WHERE q.userid.userId = :userId GROUP BY d.id ORDER BY COUNT(d) DESC")
    List<Object[]> findTopTenDestinationsForUser(@Param("userId") Long userId);
     
     
     
     
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
        
     	
}
 
 
 
 