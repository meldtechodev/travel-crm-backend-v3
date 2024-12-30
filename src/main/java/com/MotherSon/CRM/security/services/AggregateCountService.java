package com.MotherSon.CRM.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.AggregateCountDTO;

@Service
public class AggregateCountService {
	
	private final QueryBookService querybookService;
    private final UserService userService;
    private final CustomerService customerService;
    private final BookingService bookingService;

    @Autowired
    public AggregateCountService(QueryBookService querybookService, UserService userService, CustomerService customerService, BookingService bookingService) {
        this.querybookService = querybookService;
        this.userService = userService;
        this.customerService = customerService;
        this.bookingService = bookingService;
    }

//    public AggregateCountDTO getAggregateCounts() {
//        AggregateCountDTO countDTO = new AggregateCountDTO();
//
//        // Set values for querybook counts
//        countDTO.setTotalQueryBook(querybookService.getTotalQueryBook());
//        countDTO.setActiveQueryBook(querybookService.getActiveQueryBook());
//
//        // Set values for user counts
//        countDTO.setTotalUser(userService.getTotalUser());
//        countDTO.setActiveUser(userService.getActiveUser());
//
//        // Set values for customer counts
//        countDTO.setTotalCustomer(customerService.getTotalCustomer());
//        countDTO.setActiveCustomer(customerService.getActiveCustomer());
//
//        // Set values for booking counts
//        countDTO.setTotalBookings(bookingService.getTotalBookings());
//        countDTO.setActiveBookings(bookingService.getActiveBookings());
//
//        return countDTO;
//    }
}


