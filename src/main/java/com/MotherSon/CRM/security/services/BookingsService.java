package com.MotherSon.CRM.security.services;
 
import java.util.List;

import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
 
import com.MotherSon.CRM.dto.BookingsDTO;

import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.Bookings;

import com.MotherSon.CRM.repository.BookingsRepository;
 
@Service

public class BookingsService {

	@Autowired

	private BookingsRepository bookingsRepository;

	public Bookings addBookings(Bookings bookings) {

		Bookings book = bookingsRepository.save(bookings);

		return book;

	}

 
 
	 public Bookings findById(Long id) {

	        return bookingsRepository.findById(id)

	                .orElseThrow(() -> new CustomException("Booking not found", "BOOKING_NOT_FOUND"));

	    }
 
	    public Bookings updateBooking(Long id, Bookings updatedBooking) {

	        Bookings existingBooking = findById(id);

	        // Update fields

	        existingBooking.setCreatedby(updatedBooking.getCreatedby());

	        existingBooking.setModifiedby(updatedBooking.getModifiedby());

	        existingBooking.setIpaddress(updatedBooking.getIpaddress());

	        existingBooking.setDiscountRequested(updatedBooking.getDiscountRequested());

	        existingBooking.setFinalPrice(updatedBooking.getFinalPrice());

	        existingBooking.setPaymentStatus(updatedBooking.getPaymentStatus());

	        existingBooking.setEmailSent(updatedBooking.isEmailSent());

	        existingBooking.setStatus(updatedBooking.isStatus());

	        existingBooking.setIsdelete(updatedBooking.isIsdelete());

	        existingBooking.setCustomer(updatedBooking.getCustomer());

	        existingBooking.setQuery(updatedBooking.getQuery());

	        existingBooking.setPack(updatedBooking.getPack());

	        existingBooking.setHotel(updatedBooking.getHotel());
 
	        // Save the updated booking

	        return bookingsRepository.save(existingBooking);

	    }


 
 
 
	public Response<Object> getAllBookings(){

		List<Bookings> filterdBookings = bookingsRepository.findAll().stream()

				.filter(bookings -> !bookings.isIsdelete())

				.collect(Collectors.toList());

		if(filterdBookings.isEmpty()) {

			throw new CustomException("Bookings Not Found", "404");

		}

		List<BookingsDTO> bookingsList = filterdBookings.stream()

				.map(this::convertToBookingsDTO).collect(Collectors.toList());

		return new Response<>("Successful", "Getting Bookings Successfully", bookingsList);

	}

	private BookingsDTO convertToBookingsDTO(Bookings bookings) {

		BookingsDTO bookingsDTO = new BookingsDTO(

				bookings.getId(),

				bookings.getDiscountRequested(),

				bookings.getFinalPrice(),

				bookings.getPaymentStatus(),

				bookings.isEmailSent(),

				bookings.getCustomer().getId(),

				bookings.getCustomer().getfName(),

				bookings.getCustomer().getlName(),

				bookings.getCustomer().getEmailId(),

				bookings.getCustomer().getContactNo(),

				bookings.getQuery().getId(),

				bookings.getQuery().getProposalId(),

				bookings.getQuery().getRequirementType(),

				bookings.getQuery().getTravelDate(),

				bookings.getPack().getId(),

				bookings.getPack().getPkName(),

				bookings.getPack().getPkCategory(),

				bookings.getHotel().getId(),

				bookings.getHotel().getHname(),

				bookings.getHotel().getHcontactname(),

				bookings.getHotel().getHcontactnumber(),

				bookings.getHotel().getHcontactemail(),

				bookings.getHotel().getHaddress()

				);

		return bookingsDTO;

	}


	public Response<Object> getBookingsByCustomerId(Long customerId){

		List<Bookings> bookingsList = bookingsRepository.findByCustomerId(customerId).stream()

				.filter(bookings -> !bookings.isIsdelete())

				 .collect(Collectors.toList());

				if(bookingsList.isEmpty()) {

					throw new CustomException("Not Found", "404");

				}

				List<BookingsDTO> bookingsDTOList = bookingsList.stream()

						.map(this::convertToBookingsDTO).collect(Collectors.toList());

				return new Response<>("Successful", "Getting Bookings Successfully", bookingsDTOList);

	}

private BookingsDTO convertToBookingsDTO1(Bookings bookings) {

		BookingsDTO bookingsDTO = new BookingsDTO(

				bookings.getId(),

				bookings.getDiscountRequested(),

				bookings.getFinalPrice(),

				bookings.getPaymentStatus(),

				bookings.isEmailSent(),

				bookings.getCustomer().getId(),

				bookings.getCustomer().getfName(),

				bookings.getCustomer().getlName(),

				bookings.getCustomer().getEmailId(),

				bookings.getCustomer().getContactNo(),

				bookings.getQuery().getId(),

				bookings.getQuery().getProposalId(),

				bookings.getQuery().getRequirementType(),

				bookings.getQuery().getTravelDate(),

				bookings.getPack().getId(),

				bookings.getPack().getPkName(),

				bookings.getPack().getPkCategory(),

				bookings.getHotel().getId(),

				bookings.getHotel().getHname(),

				bookings.getHotel().getHcontactname(),

				bookings.getHotel().getHcontactnumber(),

				bookings.getHotel().getHcontactemail(),

				bookings.getHotel().getHaddress()

				);

		return bookingsDTO;

	}
 
 
public Response<Object> getBookingsByqueryId(Long queryId){

	List<Bookings> bookingsList = bookingsRepository.findByqueryId(queryId).stream()

			.filter(bookings -> !bookings.isIsdelete())

			 .collect(Collectors.toList());

			if(bookingsList.isEmpty()) {

				throw new CustomException("Not Found", "404");

			}

			List<BookingsDTO> bookingsDTOList = bookingsList.stream()

					.map(this::convertToBookingsDTO).collect(Collectors.toList());

			return new Response<>("Successful", "Getting Bookings Successfully", bookingsDTOList);

}
 
private BookingsDTO convertToBookingsDTO2(Bookings bookings) {

	BookingsDTO bookingsDTO = new BookingsDTO(

			bookings.getId(),

			bookings.getDiscountRequested(),

			bookings.getFinalPrice(),

			bookings.getPaymentStatus(),

			bookings.isEmailSent(),

			bookings.getCustomer().getId(),

			bookings.getCustomer().getfName(),

			bookings.getCustomer().getlName(),

			bookings.getCustomer().getEmailId(),

			bookings.getCustomer().getContactNo(),

			bookings.getQuery().getId(),

			bookings.getQuery().getProposalId(),

			bookings.getQuery().getRequirementType(),

			bookings.getQuery().getTravelDate(),

			bookings.getPack().getId(),

			bookings.getPack().getPkName(),

			bookings.getPack().getPkCategory(),

			bookings.getHotel().getId(),

			bookings.getHotel().getHname(),

			bookings.getHotel().getHcontactname(),

			bookings.getHotel().getHcontactnumber(),

			bookings.getHotel().getHcontactemail(),

			bookings.getHotel().getHaddress()

			);

	return bookingsDTO;

}
 
 
	public Response<Object> getBookingsByPackId(Long packId){

		List<Bookings> bookingsList = bookingsRepository.findByPackId(packId).stream()

			.filter(bookings -> !bookings.isIsdelete())

			 .collect(Collectors.toList());

			if(bookingsList.isEmpty()) {

				throw new CustomException("Not Found", "404");

			}

			List<BookingsDTO> bookingsDTOList = bookingsList.stream()

					.map(this::convertToBookingsDTO).collect(Collectors.toList());

			return new Response<>("Successful", "Getting Bookings Successfully", bookingsDTOList);

}
 
 
 
private BookingsDTO convertToBookingsDTO3(Bookings bookings) {

	BookingsDTO bookingsDTO = new BookingsDTO(

			bookings.getId(),

			bookings.getDiscountRequested(),

			bookings.getFinalPrice(),

			bookings.getPaymentStatus(),

			bookings.isEmailSent(),

			bookings.getCustomer().getId(),

			bookings.getCustomer().getfName(),

			bookings.getCustomer().getlName(),

			bookings.getCustomer().getEmailId(),

			bookings.getCustomer().getContactNo(),

			bookings.getQuery().getId(),

			bookings.getQuery().getProposalId(),

			bookings.getQuery().getRequirementType(),

			bookings.getQuery().getTravelDate(),

			bookings.getPack().getId(),

			bookings.getPack().getPkName(),

			bookings.getPack().getPkCategory(),

			bookings.getHotel().getId(),

			bookings.getHotel().getHname(),

			bookings.getHotel().getHcontactname(),

			bookings.getHotel().getHcontactnumber(),

			bookings.getHotel().getHcontactemail(),

			bookings.getHotel().getHaddress()

			);

	return bookingsDTO;
	}
}
