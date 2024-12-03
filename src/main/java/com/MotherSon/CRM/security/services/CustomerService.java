package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Customer;
import com.MotherSon.CRM.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public boolean existsByEmailId(String emailId) {
        return customerRepository.existsByEmailId(emailId);
    }
	
	public boolean existsByContactNo(String contactNo) {
		return customerRepository.existsByContactNo(contactNo);
	}
	
	
	public boolean existsByAdharNo(String adharNo) {
		return customerRepository.existsByAdharNo(adharNo);
	}
	
	public boolean existsByPassportId(String passportId) {
		return customerRepository.existsByPassportId(passportId);
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Customer c) {
		return  customerRepository.save(c);
	}

	
	
	public void deletedById(long id) {
		customerRepository.deleteById(id);
	}
	

	public Customer findById(long id) {
		return null;
	}

	
//	public List<Customer> getAllCustomer() {
//		return customerRepository.findAll();
//	}
	
	
	
public Page<Customer> getCustomer(int page, int size, String sortDirection){
		
		Sort sort = Sort.by(Sort.Order.asc("fName"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("fName"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		
		return customerRepository.findAll(pageable);
			
			
			
		}
	
	

	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}
	}


