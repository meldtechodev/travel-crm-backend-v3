package com.MotherSon.CRM.repository;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
 
 
	
}
 
 
 
 