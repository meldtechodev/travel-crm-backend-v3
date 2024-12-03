package com.MotherSon.CRM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.models.booking.Booking;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	
	
	
   List<Booking> findByBookingByUserId(Long bookingByUserId);
   
   
  // List<Booking> findByBookingByUserIdAndBooking_Status(Long bookingByUserId, String booking_Status);


List<Booking> findByBookingByUserIdAndBookingStatusIgnoreCase(Long bookingByUserId, String bookingStatus);



@Query("SELECT u FROM Booking u WHERE u.bookingStatus = :bookingStatus")
List<Booking> findByBookingStatus(String bookingStatus);


List<Booking> findByBookingStatusAndBookingByUserId(String status, Long userId);


List<Pkg> findByDestinationId(Long destinationId);


//List<Booking> findAllConfirmedBookings();
//
//
//
////List<Booking> findByBookingStatus(String bookingStatus);
//@Query("SELECT u FROM Booking u WHERE u.bookingStatus = 'Pending'")
//List<Booking> findAllPendingBookings();
//
//
//
//@Query("SELECT u FROM Booking u WHERE u.bookingStatus = 'Cancle'")
//List<Booking> findAllCancleBookings();
//	
//	
}