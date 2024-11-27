package com.MotherSon.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Transport;
import com.MotherSon.CRM.security.services.TransportService;

@RestController
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("Motherson/crm/v1/transport")
	public class TransportController {
		
		
		@Autowired
		private TransportService transportService;
		
		
		@GetMapping("/getAll")
		public List<Transport> getAllTransport(){
			return transportService.getAllTransport();
		}
		
		
		@PostMapping("/create")
	    public Transport addTransport(@RequestBody Transport transport) {
	  	return this.transportService.addTransport(transport);

	}
	}

