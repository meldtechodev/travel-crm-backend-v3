package com.MotherSon.CRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.dto.AggregateCountDTO;
import com.MotherSon.CRM.security.services.AggregateCountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Motherson/crm/v1/dashbord")
public class AggregateCountController {
	
	private final AggregateCountService aggregateCountService;

    @Autowired
    public AggregateCountController(AggregateCountService aggregateCountService) {
        this.aggregateCountService = aggregateCountService;
    }

//    @GetMapping("/active/count")
//    public ResponseEntity<AggregateCountDTO> getAggregateCounts() {
//        AggregateCountDTO aggregateCountDTO = aggregateCountService.getAggregateCounts();
//        return ResponseEntity.ok(aggregateCountDTO);
//    }
}


