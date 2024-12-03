package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.models.booking.Booking;
import com.MotherSon.CRM.repository.BookingRepository;
import com.MotherSon.CRM.repository.HotelServiceImpl;
import com.MotherSon.CRM.repository.PkgRepository;



@Service
public class BookingService {

	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PkgRepository pkgRepository;
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	public  Booking addBooking(Booking booking) {
		return bookingRepository.save(booking);

}
	
	
	public Booking updateBookingById(Long id, Booking bookingDetails) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existingBooking.setBookingStatus(bookingDetails.getBookingStatus());
        existingBooking.setBookingByUserId(bookingDetails.getBookingByUserId());
        existingBooking.setBookingByUserName(bookingDetails.getBookingByUserName());
        existingBooking.setTravelDetails(bookingDetails.getTravelDetails());
        existingBooking.setPaymentDetails(bookingDetails.getPaymentDetails());
       // existingBooking.setSpecialRequest(bookingDetails.getSpecialRequest());

        return bookingRepository.save(existingBooking);
    }

	
	public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }


//	public Booking updateBooking(Long id, Booking booking) {
//		// TODO Auto-generated method stub
//		return bookingServiceImpl.findById(id)
//				
//				.map(existingBooking -> {
//		
//		
//		
//		if(booking.getTravelDetails() != null) {
//			existingBooking.setTravelDetails(booking.getTravelDetails());	
//		}
//		
//		
//		
//		if(booking.getPaymentDetails() != null) {
//			existingBooking.setPaymentDetails(booking.getPaymentDetails());
//        }
//		
//		
//		
//		if(booking.getSpecialRequest() != null) {
//			existingBooking.setSpecialRequest(booking.getSpecialRequest());
//		}
//		
//		
//
//		return bookingServiceImpl.save(existingBooking);
//		})
//				
//				.orElseThrow(() -> new RuntimeException("Booking not Found"));
//	
//
//				
//				
//		
//	
		

	public void findById(Long id) {
		bookingRepository.deleteById(id);
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Booking> getAllBooking() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

//	public Optional<Booking> getBookingById(Long id) {
//		 //TODO Auto-generated method stub
//		return bookingServiceImpl.findById(id);
//}

	public List<Booking> findBookingsByUserId(Long bookingByUserId) {
    return bookingRepository.findByBookingByUserId(bookingByUserId);
		
		//return bookingServiceImpl.findBookingByUserId(bookingByUserId);
    
	//@SuppressWarnings("rawtypes")
	//public Booking<Booking> getBookingById(Long id) {
		// TODO Auto-generated method stusb
		//return bookingServiceImpl.findById(id);
		//.orElseThrow(()   -> new RuntimeException("Booking Not Find"));
	
	
	}
	
	public List<Booking> getConfirmedBookingsByUserId(Long bookingByUserId) {
        return bookingRepository.findByBookingByUserIdAndBookingStatusIgnoreCase(bookingByUserId, "Confirmed");
}
	
//	public List<Booking> getPendingBookings(Long bookingByUserId) {
//        return bookingServiceImpl.findByBookingByUserIdAndBookingStatus(bookingByUserId, "Pending");
//}}
	
	
	public List<Booking> getPendingBookingsByUserId(Long bookingByUserId) {
		return bookingRepository.findByBookingByUserIdAndBookingStatusIgnoreCase(bookingByUserId, "Pending");
}

	
	
	public List<Booking> getCancleBookiByUserId(Long bookingByUserId) {
		return bookingRepository.findByBookingByUserIdAndBookingStatusIgnoreCase(bookingByUserId, "Cancle");
	}
	
	
	
//	public List<Booking> getAllConfirmedBookings() {
//        return bookingServiceImpl.findByBookingStatusIgnoreCase("Confirmed");
//	}
//        
//	
//	
//	public List<Booking> getAllPendingBookings() {
//        return bookingServiceImpl.findByBookingStatusIgnoreCase("Pending");
//	}
	
	
	public List<Booking> getAllCancleBooking(String name){
		return bookingRepository.findByBookingStatus(name);
		
	}

	public List<Pkg> getPackagesByDestinationId(Long destinationId) {
		return bookingRepository.findByDestinationId(destinationId);
	}
	
//	public List<Hotel> gethotelByDestinationIdAndStar_ratings(Long destinationid, String rating) {
//		return hotelServiceImpl.findByDestinationIdAndStar_ratings(destinationid, rating);
//	}
	  
 
	// By max destination
	public Map<String, Long> getTopConfirmedDestinations(Long userId, int limit) {
	    List<Booking> confirmedBookings = bookingRepository.findByBookingStatusAndBookingByUserId("Confirmed", userId);
 
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