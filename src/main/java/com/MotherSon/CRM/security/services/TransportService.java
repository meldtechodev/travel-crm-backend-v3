package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

		
//		public List<Transport> getAllTransport(){
//			return transportRepository.findAll();
//		}
		
		
		public Page<Transport> getTransport(int page , int size , String sortDirection){
			Sort sort = Sort.by(Sort.Order.asc("transportmode"));
			
			if("desc".equalsIgnoreCase(sortDirection)) {
				sort = Sort.by(Sort.Order.desc("transportmode"));
			}
			
			
			PageRequest  pageable = PageRequest.of(page, size, sort);
			return transportRepository.findAll(pageable);
		}
	}

