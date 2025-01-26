package com.MotherSon.CRM.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
 
import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.ErrorResponse;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.BookingDetails;

import com.MotherSon.CRM.models.Bookings;

import com.MotherSon.CRM.security.services.BookingDetailsService;
 
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping("/Motherson/crm/v1/bookingdetails")

public class BookingDetailsController {


	@Autowired

	private BookingDetailsService bookingdetailsService;


	@GetMapping("all")

	public ResponseEntity<Response<Object>> getAllBookingDetails(){

		try {

			Response<Object> response = bookingdetailsService.getAllBookingDetails();

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}


	@GetMapping("/bookings/{bookingsId}")

	public ResponseEntity<Response<Object>> getBookingDetailsByBookingsId(@PathVariable Long bookingsId){

		try {

			Response<Object> response = bookingdetailsService.getBookingDetailsByBookingsId(bookingsId);

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}


	@GetMapping("/{id}")

	public ResponseEntity<Response<Object>> getBookingDetailsId(@PathVariable Long id){

		try {

			Response<Object> response = bookingdetailsService.getBookingDetailsById(id);

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}


	@PostMapping("/create")

	public ResponseEntity<Response<?>> addBookingDetails(@RequestBody BookingDetails bookingDetails){

		try {

			BookingDetails bookingDetail = bookingdetailsService.addBookingDetails(bookingDetails);

			Response<String> response = new Response<>("SUCCESS", "BookingDetails add Successfully", "created");

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

	}
 