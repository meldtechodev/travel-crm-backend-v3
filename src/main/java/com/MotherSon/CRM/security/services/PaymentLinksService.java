package com.MotherSon.CRM.security.services;
 
import java.util.List;

import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
 
import com.MotherSon.CRM.dto.BookingDetailsDTO;

import com.MotherSon.CRM.dto.CustomException;

import com.MotherSon.CRM.dto.PaymentLinksDTO;

import com.MotherSon.CRM.dto.Response;

import com.MotherSon.CRM.models.BookingDetails;

import com.MotherSon.CRM.models.PaymentLinks;

import com.MotherSon.CRM.repository.PaymentLinksRepository;
 
@Service
public class PaymentLinksService {


	@Autowired
	private PaymentLinksRepository paymentlinksRepository;


	public Response<Object> getAllPaymentLinks(){

		List<PaymentLinks> filterdPaymentLinks = paymentlinksRepository.findAll().stream()

				.filter(paymentLinks -> !paymentLinks.isIsdelete())

				.collect(Collectors.toList());

		if(filterdPaymentLinks.isEmpty()) {

			throw new CustomException("BookingDetails Not Found", "404");

		}

		List<PaymentLinksDTO> paymentLinksDetailsList = filterdPaymentLinks.stream()

				.map(this::convertToPaymentLinksDTO).collect(Collectors.toList());

		return new Response<>("Successful", "Getting PaymentLinks Successfully", paymentLinksDetailsList);

	}

	private PaymentLinksDTO convertToPaymentLinksDTO(PaymentLinks paymentLinks) {

		PaymentLinksDTO paymentLinksDTO = new PaymentLinksDTO(

				paymentLinks.getId(),

				paymentLinks.getPaymentLink(),

				paymentLinks.getBookings().getId(),

				paymentLinks.getBookings().getFinalPrice(),

				paymentLinks.getBookings().getPaymentStatus(),

				paymentLinks.getBookings().isEmailSent()

				);

		return paymentLinksDTO;

	}


	public PaymentLinks addPaymentLinks(PaymentLinks paymentLinks) {

		PaymentLinks paymentLink = paymentlinksRepository.save(paymentLinks);

		return paymentLink;

	}
 
}
