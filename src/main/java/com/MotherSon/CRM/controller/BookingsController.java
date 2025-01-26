package com.MotherSon.CRM.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
 
import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.ErrorResponse;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.Bookings;

import com.MotherSon.CRM.models.Customer;

import com.MotherSon.CRM.models.Hotel;

import com.MotherSon.CRM.models.Pkg;

import com.MotherSon.CRM.models.QueryBook;

import com.MotherSon.CRM.security.services.BookingsService;

import com.MotherSon.CRM.security.services.CustomerService;

import com.MotherSon.CRM.security.services.HotelService;

import com.MotherSon.CRM.security.services.PkgService;

import com.MotherSon.CRM.security.services.QueryBookService;
 
 
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping("/Motherson/crm/v1/bookings")

public class BookingsController {

	@Autowired

	private BookingsService bookingsService;

	@Autowired

	private QueryBookService querybookService;

	@Autowired

	private CustomerService customerService;

	@Autowired

	private PkgService pkgService;

	@Autowired

	private HotelService hotelService;


	@GetMapping("all")

	public ResponseEntity<Response<Object>> getAllBookings(){

		try {

			Response<Object> response = bookingsService.getAllBookings();

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}

	@GetMapping("/customer/{customerId}")

	public ResponseEntity<Response<Object>> getBookingsByCustomerId(@PathVariable Long customerId){

		try {

			Response<Object> response = bookingsService.getBookingsByCustomerId(customerId);

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}

	@GetMapping("/query/{queryId}")

	public ResponseEntity<Response<Object>> getBookingsByQueryId(@PathVariable Long queryId){

		try {

			Response<Object> response = bookingsService.getBookingsByqueryId(queryId);

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}


	@GetMapping("/package/{packId}")

	public ResponseEntity<Response<Object>> getBookingsByPackId(@PathVariable Long packId){

		try {

			Response<Object> response = bookingsService.getBookingsByPackId(packId);

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}

	@PostMapping("/create")

	public ResponseEntity<Response<?>> addBookings(@RequestBody Bookings bookings){

		try {

			Bookings book = bookingsService.addBookings(bookings);

			Response<String> response = new Response<>("SUCCESS", "Bookings add Successfully", "created");

			return new ResponseEntity<>(response, HttpStatus.CREATED);

		}catch(CustomException e) {

			ErrorResponse errorResponse = new ErrorResponse("FAILURE", e.getMessage(), e.getErrorCode());

			Response<ErrorResponse> response = new Response<>("FAILURE", "Validation error", errorResponse);

			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch(Exception e) {

			 ErrorResponse errorResponse = new ErrorResponse("FAILURE", "An unexpected error occurred: " + e.getMessage(), "500");

		        Response<ErrorResponse> response = new Response<>("FAILURE", "Internal server error", errorResponse);

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}


	@PutMapping("/{id}")

	public ResponseEntity<Response<String>> updateBooking(

	        @PathVariable("id") Long id,

	        @RequestBody Bookings updatedBooking) {
 
 
	    Customer customer = customerService.getCustomersById(updatedBooking.getCustomer().getId());

	    if (customer == null) {

	        throw new CustomException("Customer not found", "CUSTOMER_NOT_FOUND");

	    }
 
	    QueryBook query = querybookService.getQueryBookById(updatedBooking.getQuery().getId());

	    if (query == null) {

	        throw new CustomException("QueryBook not found", "QUERYBOOK_NOT_FOUND");

	    }
 
	    Pkg pkg = pkgService.getPkgById(updatedBooking.getPack().getId());

	    if (pkg == null) {

	        throw new CustomException("Package not found", "PACKAGE_NOT_FOUND");

	    }
 
	    Hotel hotel = hotelService.getHotelById(updatedBooking.getHotel().getId());

	    if (hotel == null) {

	        throw new CustomException("Hotel not found", "HOTEL_NOT_FOUND");

	    }
 
	    Bookings existingBooking = bookingsService.findById(id);

	    if (existingBooking == null) {

	        throw new CustomException("Booking not found", "BOOKING_NOT_FOUND");

	    }
 
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

	    existingBooking.setCustomer(customer);

	    existingBooking.setQuery(query);

	    existingBooking.setPack(pkg);

	    existingBooking.setHotel(hotel);
 
	    // Save the updated booking

	    Bookings updated = bookingsService.updateBooking(id, existingBooking);
 
	    // Create response object

	    Response<String> response = new Response<>("success", "Booking updated successfully", "Updated");

	    return ResponseEntity.ok(response);

	}
 
}