package com.MotherSon.CRM.repository;
 
import java.util.Collection;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
 
import com.MotherSon.CRM.models.BookingDetails;
 
@Repository

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
 
	Collection<BookingDetails> findByBookingsId(Long bookingsId);
 
	//BookingDetails save(BookingDetails bookingDetails);
 
}