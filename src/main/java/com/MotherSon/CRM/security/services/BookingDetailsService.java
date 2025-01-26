package com.MotherSon.CRM.security.services;
 
import java.util.List;

import java.util.Optional;

import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
 
import com.MotherSon.CRM.dto.BookingDetailsDTO;

import com.MotherSon.CRM.dto.BookingsDTO;

import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.BookingDetails;

import com.MotherSon.CRM.models.Bookings;

import com.MotherSon.CRM.repository.BookingDetailsRepository;
 
 
@Service
public class BookingDetailsService {
 
	

	@Autowired
	private BookingDetailsRepository bookingdetailsRepository;


	public Response<Object> getAllBookingDetails(){

		List<BookingDetails> filterdBookingDetails = bookingdetailsRepository.findAll().stream()

				.filter(bookings -> !bookings.isIsdelete())

				.collect(Collectors.toList());

		if(filterdBookingDetails.isEmpty()) {

			throw new CustomException("BookingDetails Not Found", "404");

		}

		List<BookingDetailsDTO> bookingDetailsList = filterdBookingDetails.stream()

				.map(this::convertToBookingDetailsDTO).collect(Collectors.toList());

		return new Response<>("Successful", "Getting BookingDetails Successfully", bookingDetailsList);

	}

	private BookingDetailsDTO convertToBookingDetailsDTO(BookingDetails bookingDetails) {

		BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO(

				bookingDetails.getId(),

				bookingDetails.getDetailKey(),

				bookingDetails.getDetailValue(),

				bookingDetails.getBookings().getId(),

				bookingDetails.getBookings().getFinalPrice(),

				bookingDetails.getBookings().getPaymentStatus(),

				bookingDetails.getBookings().isEmailSent()

				);

		return bookingDetailsDTO;

	}


	public Response<Object> getBookingDetailsByBookingsId(Long bookingsId){

		List<BookingDetails> bookingDetailsList = bookingdetailsRepository.findByBookingsId(bookingsId).stream()

				.filter(bookingDetails -> !bookingDetails.isIsdelete())

				 .collect(Collectors.toList());

				if(bookingDetailsList.isEmpty()) {

					throw new CustomException("Not Found", "404");

				}

				List<BookingDetailsDTO> bookingDetailsDTOList = bookingDetailsList.stream()

						.map(this::convertToBookingDetailsDTO).collect(Collectors.toList());

				return new Response<>("Successful", "Getting BookingDetails Successfully", bookingDetailsDTOList);

	}

private BookingDetailsDTO convertToBookingDetailsDTO1(BookingDetails bookingDetails) {

		BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO(

				bookingDetails.getId(),

				bookingDetails.getDetailKey(),

				bookingDetails.getDetailValue(),

				bookingDetails.getBookings().getId(),

				bookingDetails.getBookings().getFinalPrice(),

				bookingDetails.getBookings().getPaymentStatus(),

				bookingDetails.getBookings().isEmailSent()

				);

		return bookingDetailsDTO;

	}
 
 
//Get BookingDetails by BookingDetails Id

	public Response<Object> getBookingDetailsById(Long id){

		Optional<BookingDetails> bookingDetailsOptional = bookingdetailsRepository.findById(id);

		if(bookingDetailsOptional.isEmpty()) {

			throw new CustomException("BookingDetails Not Found", "404");

		}

		List<BookingDetailsDTO> bookingDetailsDTOList = bookingDetailsOptional.stream()

				.map(this::convertToBookingDetailsDTO).collect(Collectors.toList());

		return new Response<>("Successful", "Getting BookingDetails Successfully", bookingDetailsDTOList);

	}

private BookingDetailsDTO convertToBookingDetailsDTO2(BookingDetails bookingDetails) {

		BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO(

				bookingDetails.getId(),

				bookingDetails.getDetailKey(),

				bookingDetails.getDetailValue(),

				bookingDetails.getBookings().getId(),

				bookingDetails.getBookings().getFinalPrice(),

				bookingDetails.getBookings().getPaymentStatus(),

				bookingDetails.getBookings().isEmailSent()

				);

		return bookingDetailsDTO;

	}



	public BookingDetails addBookingDetails(BookingDetails bookingDetails) {

		BookingDetails bookingDetail = bookingdetailsRepository.save(bookingDetails);

		return bookingDetail;

	}

}