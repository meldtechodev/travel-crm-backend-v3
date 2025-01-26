package com.MotherSon.CRM.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
 
import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.ErrorResponse;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.BookingDetails;

import com.MotherSon.CRM.models.PaymentLinks;

import com.MotherSon.CRM.security.services.PaymentLinksService;
 
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping("/Motherson/crm/v1/paymentlinks")

public class PaymentLinksController {


	@Autowired

	private PaymentLinksService paymentlinksService;


	@GetMapping("all")

	public ResponseEntity<Response<Object>> getAllPaymentLinks(){

		try {

			Response<Object> response = paymentlinksService.getAllPaymentLinks();

			return new ResponseEntity<>(response, HttpStatus.OK);

		}catch(Exception e) {

			throw e;

		}

	}


	@PostMapping("/create")

	public ResponseEntity<Response<?>> addPaymentLinks(@RequestBody PaymentLinks paymentLinks){

		try {

			PaymentLinks paymentLink = paymentlinksService.addPaymentLinks(paymentLinks);

			Response<String> response = new Response<>("SUCCESS", "PaymentLinks add Successfully", "created");

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
 