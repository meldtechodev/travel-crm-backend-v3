package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Customer;
import com.MotherSon.CRM.models.CustomerResponse;
import com.MotherSon.CRM.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
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
	
	

	
	
	
	
	public ResponseEntity<?> saveCustomer(Customer customer) {

        // Check if the emailId is already taken
		
		
//        if (customerRepository.existsByEmailId(customer.getEmailId())) {
//            return new ResponseEntity<>("EmailId  already exists.", HttpStatus.CONFLICT);
//        }
		
		   
	      if (customerRepository.existsByContactNo(customer.getContactNo())) {
	      // Fetch the existing customer with the same emailId
	      Customer existingCustomer = customerRepository.findByContactNo(customer.getContactNo());
	 
	 
	      return new ResponseEntity<>(
	              new CustomerResponse("Customer already exists.", existingCustomer),
	              HttpStatus.CONFLICT);
	  }
	        
	 
	        
	        if (customer.getAdharNo() != null && !customer.getAdharNo().isEmpty() && customerRepository.existsByAdharNo(customer.getAdharNo())) {
	            return new ResponseEntity<>("Aadhar Number already exists.", HttpStatus.OK);
	        }
	 
	       
	        if (customer.getPassportId() != null && !customer.getPassportId().isEmpty() && customerRepository.existsByPassportId(customer.getPassportId())) {
	            return new ResponseEntity<>("Passport ID already exists.", HttpStatus.OK);
	        }
	 
	        
	        Customer savedCustomer = customerRepository.save(customer);
	        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED); // Return created customer object
	    }
		
	 
            
        
		
//        if (customerRepository.existsByEmailId(customer.getEmailId())) {
//            // Fetch the existing customer with the same emailId
//            Customer existingCustomer = customerRepository.findByEmailId(customer.getEmailId());
//
//            // Return the response with conflict status and include the existing customer data
//            return new ResponseEntity<>(
//                    new CustomerResponse("Email ID already exists.", existingCustomer), 
//                    HttpStatus.CONFLICT);
//        }

//        // Check if the contactNo is already taken
//        if (customerRepository.existsByContactNo(customer.getContactNo())) {
//            return new ResponseEntity<>("Contact Number already exists.", HttpStatus.CONFLICT);
//            
//        }
        
        
        
        
        
//      if (customerRepository.existsByContactNo(customer.getContactNo())) {
//      // Fetch the existing customer with the same emailId
//      Customer existingCustomer = customerRepository.findByContactNo(customer.getContactNo());
//
//      // Return the response with conflict status and include the existing customer data
//      return new ResponseEntity<>(
//              new CustomerResponse("Customer already exists.", existingCustomer), HttpStatus.CONFLICT);
//  }
//        
//
//        // Check if the adharNo is provided and already taken
//        if (customer.getAdharNo() != null && !customer.getAdharNo().isEmpty() && customerRepository.existsByAdharNo(customer.getAdharNo())) {
//            return new ResponseEntity<>("Aadhar Number already exists.", HttpStatus.CONFLICT);
//        }
//
//        // Check if the passportId is provided and already taken
//        if (customer.getPassportId() != null && !customer.getPassportId().isEmpty() && customerRepository.existsByPassportId(customer.getPassportId())) {
//            return new ResponseEntity<>("Passport ID already exists.", HttpStatus.CONFLICT);
//        }

//        // Save the new customer if no conflicts are found
//        Customer savedCustomer = customerRepository.save(customer);
//        return new ResponseEntity<>(savedCustomer, HttpStatus.CONFLICT); // Return created customer object
//	}
	
}
	


	
