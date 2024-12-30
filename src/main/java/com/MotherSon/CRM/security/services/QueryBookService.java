package com.MotherSon.CRM.security.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
//import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.MotherSon.CRM.models.Destination;
import com.MotherSon.CRM.models.QueryBook;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.repository.QueryBookRepository;

//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.MotherSon.CRM.models.BookQuery;
////import com.MotherSon.CRM.repository.QueryRepository;


@Service
public class QueryBookService {
	
	
	@Autowired
	private QueryBookRepository querybookRepository;
	
	
	//private final QueryRepository queryRepository;

//    @Autowired
//    public QueryService(QueryRepository queryRepository) {
//        this.queryRepository = queryRepository;
//	
//    }
	
	
//	public boolean existsByEmailId(String emailId) {
//        return queryRepository.existsByEmailId(emailId);
//    }
//	
//	
//	public boolean existsByContactNo(String contactNo) {
//        return queryRepository.existsByContactNo(contactNo);
//    }
	
	
//	public Optional<BookQuery> getQueryById(long id){
//		return queryRepository.findById(id);
//	}
	
	
	public QueryBook getquerybyid(long id)
	{
	QueryBook bookque=	querybookRepository.findById(id).orElseThrow( ()-> new RuntimeException("No Record Found"));
		return bookque;
		
	}
	
//	public QueryBook getquerybyid(Long id) {
//        return querybookRepository.findById(id)
//                .orElseThrow(() -> new QueryNotFoundException("Query not found with ID: " + id));
//    }
	
	public Map<String, Integer> getTopFivePackages() {
	    List<Object[]> topPackages = querybookRepository.findTopFivePackages();
 
	    Map<String, Integer> packageCountMap = new LinkedHashMap<>();
	    
	    // Limit to top 5 (though it's already sorted by the query)
	    for (int i = 0; i < Math.min(topPackages.size(), 5); i++) {
	        Object[] result = topPackages.get(i);
	        Long packId = (Long) result[0];
	        Integer count = ((Long) result[1]).intValue();  // Convert count to Integer
 
	        packageCountMap.put("packId " + packId, count);
	    }
 
	    return packageCountMap;
	}
 

	
	public List<QueryBook> getAllQuery(){
		List<QueryBook>querygetco= querybookRepository.findAll();
		return querygetco;
	}
	
 
	
	
	public QueryBook addQuery(QueryBook query) {
		return querybookRepository.save(query);
	}
	
	
	public QueryBook updateQuery(QueryBook qu) {
		return querybookRepository.save(qu);
	}
	
	
	public void deleteById(long id) {
		querybookRepository.deleteById(id);
	}

	public QueryBook findById(Long id) {
		return null;

}
	


	



	
	public Map<Long, Long> getQueryBookCountByDestination(User user) {
        List<Object[]> result = querybookRepository.countQueryBooksByDestination(user);

        // Map to store destinationId and the corresponding count of QueryBooks
        Map<Long, Long> destinationCountMap = new HashMap<>();

        for (Object[] row : result) {
            Long destinationId = (Long) row[0];  // First element is destinationId
            Long count = (Long) row[1];  // Second element is the count
            destinationCountMap.put(destinationId, count);
        }

        return destinationCountMap;
    }
	

	
	//this code is correct
	
	
//	public List<Object[]> getTopDestinations(int topCount) {  // this code is correct
//        Pageable pageable = PageRequest.of(0, topCount); // Define the pagination with the top count
//        return querybookRepository.findTopDestinations(pageable);
//    }
	
	
	public List<Object[]> getTopDestinations(int topCount) {
        Pageable pageable = PageRequest.of(0, topCount);
        return querybookRepository.findTopDestinations(pageable);
    }
	
	
	public Object getQueries(Long customerId) {
        if (customerId != null) {
            // If customerId is provided, fetch queries for that customer
            List<QueryBook> queries = querybookRepository.findByCustomerId(customerId);
            if (queries.isEmpty()) {
                return "Customer not found with customerId: " + customerId;
            }
            return queries;
        } else {
            // If customerId is not provided, fetch all queries
            return querybookRepository.findAll();
            }
	}
	
	
	
	
	// For LeadSourse
	
	
	public Map<String, Integer> getSortedLeadSourceCounts() {
        // Fetch the leadSource counts from the repository
        List<Object[]> result = querybookRepository.findLeadSourceCounts();
        
        // Convert the result into a Map
        Map<String, Integer> leadSourceCountMap = new HashMap<>();
        for (Object[] row : result) {
            String leadSource = (String) row[0];
            Long count = (Long) row[1];
            leadSourceCountMap.put(leadSource, count.intValue());
        }

        // Sort the map by value (count) in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(leadSourceCountMap.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));  // Sort by count (descending)

        // Create a new linked hash map to maintain the order
        Map<String, Integer> sortedLeadSourceMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            sortedLeadSourceMap.put(entry.getKey(), entry.getValue());
        }

        return sortedLeadSourceMap;
    }	
	
	
	
	
	
	
//	public List<Object[]> getTopQueryCountByUserId(Long userId) {
//        // Fetch the list of queries ordered by query count for the user
//        return querybookRepository.findTopQueriesByDestinationAndUserId(userId);
//    }
//	
//	public long getTotalQueryBook() {
//        return querybookRepository.countTotalQueryBook();
//    }
 
    // Get the total number of active bookings where status = true
//    public long getActiveQueryBook() {
//        return querybookRepository.countActiveQueryBook();
//    }
 
	
	
}
	
	
//	public long getTotalQueryBook() {
//        return querybookRepository.countTotalQueryBook();
//    }
//
//    // Get the total number of active bookings where status = true
//    public long getActiveQueryBook() {
//        return querybookRepository.countActiveQueryBook();
//    }
//}