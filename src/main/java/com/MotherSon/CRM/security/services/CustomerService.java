package com.MotherSon.CRM.security.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.CustomException;
import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Customer;
import com.MotherSon.CRM.models.CustomerResponse;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.CustomerRepository;
import com.MotherSon.CRM.repository.UserRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
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
public long getTotalCustomer() {
	
	return customerRepository.countTotalCustomer();
}

public long getActiveCustomer() {
	
	return customerRepository.countActiveCustomer();
}


	
	

	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}
	
	
	// Create Service code
		public ResponseEntity<?> createCustomer(Customer customer) {
	 
	        if (customerRepository.existsByEmailId(customer.getEmailId())) {
	            throw new CustomException("EmailId already exists.", "400");
	        }
	        
	        
	        if (customer.getUser() == null || customer.getUser().getUserId() == null) {
		        throw new CustomException("User ID cannot be null.", "User_ID_REQUIRED");
		    }
	 
	        if (customerRepository.existsByContactNo(customer.getContactNo())) {
	            Customer existingCustomer = customerRepository.findByContactNo(customer.getContactNo());
	 
	            // Create the custom response for validation error with the existing customer details
	            Map<String, Object> errorData = new HashMap<>();
	            errorData.put("status", "FAILURE");
	            errorData.put("message", "Customer already exists with this ContactNo.");
	            errorData.put("errorCode", "409");
	            errorData.put("customer", existingCustomer);
	 
	            Map<String, Object> response = new HashMap<>();
	            response.put("status", "FAILURE");
	            response.put("message", "Validation error");
	            response.put("data", errorData);
	 
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	        }
	 
	        if (customer.getAdharNo() != null && !customer.getAdharNo().isEmpty() && customerRepository.existsByAdharNo(customer.getAdharNo())) {
	            throw new CustomException("Aadhar Number already exists.", "AADHAR_EXISTS");
	        }
	 
	       
	        if (customer.getPassportId() != null && !customer.getPassportId().isEmpty() && customerRepository.existsByPassportId(customer.getPassportId())) {
	            throw new CustomException("Passport ID already exists.", "PASSPORT_EXISTS");
	        }
	        
	        
	        User user = userRepository.findById(customer.getUser().getUserId())
		            .orElseThrow(() -> new CustomException("User with ID " + customer.getUser().getUserId() + " does not exist.", "400"));
		    customer.setUser(user);
	 
	        Customer savedCustomer = customerRepository.save(customer);
	 
	        Map<String, Object> successResponse = new HashMap<>();
	        successResponse.put("status", "Successful");
	        successResponse.put("message", "Created successfully");
	        successResponse.put("customer", savedCustomer);
	 
	        return new ResponseEntity<>(successResponse, HttpStatus.CREATED); // HTTP 201 Created
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
	
	
	public ResponseEntity<?> checkCustomerExistence(String emailId, String contactNo) {
	    // Check if email ID exists in the repository
	    if (customerRepository.existsByEmailId(emailId)) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", "FAILURE");
	        errorResponse.put("message", "EmailId already exists.");
	        errorResponse.put("errorCode", "409");
 
	        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	    }
 
	    // Check if contact number exists in the repository
	    if (customerRepository.existsByContactNo(contactNo)) {
	        Customer existingCustomer = customerRepository.findByContactNo(contactNo);
 
	        Map<String, Object> errorData = new HashMap<>();
	        errorData.put("status", "FAILURE");
	        errorData.put("message", "Customer already exists with this Contact number");
	        errorData.put("errorCode", "409");
	        errorData.put("customer", existingCustomer);
 
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "FAILURE");
	        response.put("message", "Validation error");
	        response.put("data", errorData);
 
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    }
 
	    // If no customer exists with the given email or contact, return success response
	    Map<String, Object> successResponse = new HashMap<>();
	    successResponse.put("status", "SUCCESS");
	    successResponse.put("message", "Customer email and contact are available.");
 
	    return new ResponseEntity<>(successResponse, HttpStatus.OK);
	}
 
 
}
	


	
