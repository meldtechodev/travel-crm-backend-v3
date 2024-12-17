package com.MotherSon.CRM.security.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.booking.Booking;
import com.MotherSon.CRM.repository.BookingRepository;
import com.MotherSon.CRM.repository.DestinationRepository;


@Service
public class BookingService {
	
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@Autowired
	private DestinationRepository destinationrepository;
	
	
	
	public List<Booking> getAllBooking(){
		List<Booking> bookinglist = bookingRepository.findAll();
		
		return bookinglist.stream()
				                  .filter(booking -> !booking.isIsdelete())
				                  .collect(Collectors.toList());
	}
		
	
	
	public Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);

}
	
	
	
	public Booking updateBookingById(Long id, Booking bookingDetails) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existingBooking.setBookingStatus(bookingDetails.getBookingStatus());
        existingBooking.setBookingByuserId(bookingDetails.getBookingByuserId());
       // existingBooking.setBookingByUserName(bookingDetails.getBookingByUserName());
        existingBooking.setTravelDetails(bookingDetails.getTravelDetails());
        existingBooking.setPaymentDetails(bookingDetails.getPaymentDetails());
        //existingBooking.setSpecialRequest(bookingDetails.getSpecialRequest());

        return bookingRepository.save(existingBooking);
    }

	
	public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
	
	
	
	
	public String deleteBooking(Long id) {
		Booking existBooking = bookingRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(" Booking Not Found "));
		
		existBooking.setIsdelete(true);
		bookingRepository.save(existBooking);
		return " Booking Deleted ";
		
	}
	
	
	public List<Booking> findBookingsByUserId(Long userId) {
        return bookingRepository.findByBookingByuserId_UserId(userId);
    }
	
	
	//Getting by userId confirmed List or cancle list or pending list
	
	
	public List<Booking> getBookingsByStatus(Long userId, String status) {
        return bookingRepository.findByBookingByuserId_UserIdAndBookingStatus(userId, status);
    }
	
	
	
	

	public Map<String, Long> getTopConfirmedDestinations_Id(Long userId, int limit) {
	    List<Booking> confirmedBookings = bookingRepository.findByBookingStatusAndBookingByuserId_UserId("Confirmed", userId);
 
	    // Count bookings by destination
	    Map<String, Long> to_DestinationCount = confirmedBookings.stream()
	            .collect(Collectors.groupingBy(b -> b.getTo_destination(), Collectors.counting()));
 
	    // Sort and get the top destinations based on the limit
	    return to_DestinationCount.entrySet().stream()
	            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
	            .limit(limit)  // Use the limit provided by the user
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


}