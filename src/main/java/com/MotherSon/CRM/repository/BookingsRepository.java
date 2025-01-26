package com.MotherSon.CRM.repository;
 
import java.util.Collection;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MotherSon.CRM.models.Bookings;
 
@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
 
	List<Bookings> findByCustomerId(Long customerId);
 
	List<Bookings> findByqueryId(Long queryId);
 
	Collection<Bookings> findByPackId(Long packId);
 
}