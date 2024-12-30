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
 
}
