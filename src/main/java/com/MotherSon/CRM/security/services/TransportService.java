package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Transport;
import com.MotherSon.CRM.repository.TransportRepository;

@Service
	public class TransportService {
		
		
		@Autowired
		private TransportRepository transportRepository;
		
		
		public Transport addTransport(Transport transport) {
		return transportRepository.save(transport);
		}

		
		public List<Transport> getAllTransport(){
			return transportRepository.findAll();
		}
	}

