package com.MotherSon.CRM.controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.QueryBook;
import com.MotherSon.CRM.models.User;
//import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.repository.PkgRepository;
import com.MotherSon.CRM.repository.UserRepository;
import com.MotherSon.CRM.security.services.QueryBookService;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/query")
public class QueryBookController {
	
	
	
	@Autowired
	private QueryBookService querybookService;
	
	
	@Autowired
	private PkgRepository pkgRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
//	@GetMapping("/getall")
//	public List<QueryBook> getAllQuery(){
//		return querybookService.getAllQuery();
//	}
	
	@GetMapping("/getall")
    public Object getAllQueries(@RequestParam(required = false) Long customerId) {
        // Call the service method to fetch queries based on the customerId or all queries
        return querybookService.getQueries(customerId);
        }
	
	
	
//	@GetMapping("/deleteById")
//	public ResponseEntity<?> deleteQueryBook(@PathVariable Long id){
//		QueryBook delQuery = querybookService.deleteQueryBook(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//	}
	
//	@GetMapping("/getquerybycustomerid/{customerId}")
//    public Object getQueriesByCustomerId(@PathVariable Long customerId) {
//        // Call the service method to fetch queries by customerId
//        return querybookService.getQueriesByCustomerId(customerId);
//        }
	
	
	@GetMapping("/getby/{id}")
	public QueryBook getQueryById(@PathVariable Long id){
		QueryBook query = querybookService.getquerybyid(id);
		return query;
	}
	
	
//	@PostMapping("/create")
//	public ResponseEntity<?> addQuery(@Valid @RequestBody Query query, BindingResult result ) {
//		
//
//		if (result.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            result.getFieldErrors().forEach(error -> 
//                errors.put(error.getField(), error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(errors);
//        }
//		
////		if (queryService.existsByEmailId(query.getEmailId())) {
////            return ResponseEntity.status(HttpStatus.CONFLICT)
////                    .body("Query with the emailId " + query.getEmailId() + " already exists.");
////        }
////		
////		
////		if (queryService.existsByContactNo(query.getContactNo())) {
////	        return ResponseEntity.status(HttpStatus.CONFLICT)
////	                .body("Query with the ContactNo " + query.getContactNo() + " already exists.");
////	    }
//		
//		Query savedQuery = queryService.addQuery(query);
//		
//		 return ResponseEntity.status(HttpStatus.CREATED).body("Query is created:");
//		 //return ResponseEntity.ok(this.queryService.addQuery(query));
//		
//	}
	
	
	@PostMapping("/create")
	public  QueryBook addQuery(@RequestBody QueryBook query) {
		
		if(query.getPackid()==null || !pkgRepository.existsById(query.getPackid())){
			 throw new IllegalArgumentException("packid does not exist");
		
		}
		QueryBook queryCreat = querybookService.addQuery(query);
		   
//		return new ResponseEntity<>("itinerary is created",HttpStatus.CREATED);
		return queryCreat;
	}
	
	
	@GetMapping("/top-five-packages")
	public ResponseEntity<Map<String, Integer>> getTopFivePackages() {
	    Map<String, Integer> topPackages = querybookService.getTopFivePackages();
	    return ResponseEntity.ok(topPackages);
	}
 
	
	@GetMapping("/lead-sources")
    public Map<String, Integer> getLeadSourceCounts() {
        return querybookService.getSortedLeadSourceCounts();
    }
	
 
	
	
	
	
	@PutMapping("/updateby/{id}")
	public ResponseEntity<QueryBook> updateQuery(@PathVariable Long id , @RequestBody QueryBook query){
		if(query != null)
		{
			QueryBook qu = new QueryBook();
			
			qu.setId(id);
			
			qu.setProposalId(query.getProposalId());
			qu.setRequirementType(query.getRequirementType());
			qu.setTravelDate(query.getTravelDate());
			//qu.setDays(query.getDays());
			qu.setNights(query.getNights());
			
			qu.setTotalTravellers(query.getTotalTravellers());
			qu.setAdults(query.getAdults());
			qu.setKids(query.getKids());
			qu.setInfants(query.getInfants());
			
			qu.setSalutation(query.getSalutation());
			qu.setFname(query.getFname());
			qu.setLname(query.getLname());
			qu.setEmailId(query.getEmailId());
			
			qu.setContactNo(query.getContactNo());
			qu.setLeadSource(query.getLeadSource());
			qu.setFoodPreferences(query.getFoodPreferences());
			qu.setBasicCost(query.getBasicCost());
			
			qu.setGst(query.getGst());
			qu.setTotalCost(query.getTotalCost());
			qu.setQuery_Date(query.getQuery_Date());
			qu.setQueryType(query.getQueryType());
			
			qu.setQueryCreatedFrom(query.getQueryCreatedFrom());
			//qu.setQueryAssigned(query.getQueryAssigned());
			qu.setEmailStatus(query.isEmailStatus());
			qu.setLeadStatus(query.getLeadStatus());
			qu.setLastUpdated_Date(query.getLastUpdated_Date());
			qu.setIpAddress(query.getIpAddress());
			 
			querybookService.updateQuery(qu);
		return ResponseEntity.ok(qu);
	}

	else
	{
		return ResponseEntity.notFound().build();
	}
	}
	
	// Count
	
	@GetMapping("/user/{userId}/count")
    public Map<Long, Long> getQueryBookCountByDestination(@PathVariable Long userId) {
        // Create a User object and set the userId
        User user = new User();
        user.setUserId(userId);

        // Call service method to get the count of QueryBooks for each destination
        return querybookService.getQueryBookCountByDestination(user);
    }

	// Perfect code
	
	@GetMapping("/topdestination")
	public ResponseEntity<?> getTopTenDestination(@RequestParam Long userId) {
	    Optional<User> userOpt = userRepository.findById(userId);
 
	    if (userOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Collections.singletonMap("error", "User not found"));
	    }
 
	    User user = userOpt.get();
	    
	    
	    Map<String, Integer> topDestination = querybookService.getTopTenDestination(user);
	    
	    return ResponseEntity.ok(topDestination);
	}
 
	

//	@GetMapping("/top-destinations")  // this code is correct
//    public List<Object[]> getTopDestinations(@RequestParam(defaultValue = "5") int topCount) {
//        return querybookService.getTopDestinations(topCount);
//    }

	
	
//	@GetMapping("/top-destinations")
//    public List<Map<String, Object>> getTopDestinations(@RequestParam(defaultValue = "5") int topCount) {
//        List<Object[]> result = querybookService.getTopDestinations(topCount);
//        
//        // Convert Object[] to a List of Maps
//        List<Map<String, Object>> response = result.stream()
//            .map(record -> {
//                Map<String, Object> map = new HashMap<>();
//                map.put("destinationId", record[0]); // destinationId
//                map.put("queryCount", record[1]);    // queryCount
//                return map;
//            })
//            .collect(Collectors.toList());
//        
//        return response;
//    }

	
	
//	@GetMapping("/lead-sources")
//    public Map<String, Integer> getLeadSourceCounts() {
//        return querybookService.getLeadSourceCounts();
//    }
	
	
	
	
	
	
	
	
	
	
	// Motherson/crm/v1/query/top-query-count?userId=1&limit=2
	
//	@GetMapping("/top-query-count")
//    public ResponseEntity<List<Object[]>> getTopQueryCount(
//            @RequestParam Long userId,  // Specify the userId for filtering
//            @RequestParam(required = false, defaultValue = "5") int limit) {  // Optional limit for top N results
//
//        // Fetch top queries for the given userId
//        List<Object[]> queryCounts = querybookService.getTopQueryCountByUserId(userId);
//
//        // Limit the results based on the user's input (limit)
//        if (queryCounts.size() > limit) {
//            queryCounts = queryCounts.subList(0, limit);  // Limit the size of the list
//        }
//
//        // Return the response
//        return ResponseEntity.ok(queryCounts);
//    }
	
	
	
	
//	@GetMapping("/count")
//    public String getQueryBookCount() {
//        long totalQueryBook = querybookService.getTotalQueryBook();
//        long activeQueryBook = querybookService.getActiveQueryBook();
//
//        // Return the counts as a simple string
//        return "TotalQueryBook " + totalQueryBook + " Active " + activeQueryBook;
//    }
	
	@GetMapping("/filter")
    public List<QueryBook> getQueriesByType(@RequestParam String queryType) {
        return querybookService.getQueriesByType(queryType);
    }
 


	
	
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<QueryBook> deleteQuery(@PathVariable Long id){
		
		try
		{
			querybookService.findById(id);
			querybookService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		finally
		{
			return ResponseEntity.notFound().build();
		}
	}
	}