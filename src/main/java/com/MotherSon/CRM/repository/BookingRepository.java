package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.models.booking.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	
	
	List<Booking> findByBookingByuserId_UserId(Long userId);

	
	//Getting by userId confirmed List or cancle list or pending list
	
	
	List<Booking> findByBookingByuserId_UserIdAndBookingStatus(Long userId, String status);
	
		

	List<Booking> findByBookingStatusAndBookingByuserId_UserId(String string, Long userId);
	
}