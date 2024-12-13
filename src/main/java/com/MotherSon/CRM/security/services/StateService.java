package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.dto.CountryStateResponse;
import com.MotherSon.CRM.models.State;
import com.MotherSon.CRM.repository.StateRepository;
//import com.ms.jwt.repository.state.StateRepository;

@Service

public class StateService {
	  @Autowired
	  
	    private StateRepository stateRepository;

	    public List<State> getAllStates() {
	        return stateRepository.findAll();
	    }
	    
	    public List<State> getAllCountries() {
	        // Fetch all countries
	        List<State> countries = stateRepository.findAll();
	        
	        // Filter countries where isDelete is false (not deleted)
	        return countries.stream()
	                        .filter(country -> !country.isIsdelete())  // Keep countries where isDelete is false
	                        .collect(Collectors.toList());
	        }
	  
	  
	  
	  public Page<State> getState(int page, int size , String sortDirection){
		  Sort sort = Sort.by(Sort.Order.asc("stateName"));
		  
		  if("desc".equalsIgnoreCase(sortDirection)) {
			  sort = Sort.by(Sort.Order.desc("stateName"));
		  }
		  
		  PageRequest pageable = PageRequest.of(page, size, sort);
		  return stateRepository.findAll(pageable);
	  }
	  


	  
	  
	  
	  

	    public Optional<State> getStatesById(Long id) {
	        return stateRepository.findById(id);
	    }

//	    public Optional<State> getStateByMasterKey(String masterKey) {
//	        return stateRepository.findByMasterKey(masterKey);
//	    }
	    
	    public boolean existsByStateName(String stateName)
	    {
	    	      
	    	       return stateRepository.existsByStateName(stateName);
			
					
					 }

	    public State createState(State state) {
	        return stateRepository.save(state);
	    }

	    
	    public State updateStateById(Long id, State stateDetails) {
	        State existingState = stateRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Country not found"));
 
	        existingState.setStateName(stateDetails.getStateName());
	        existingState.setCode(stateDetails.getCode());
	        existingState.setSimage(stateDetails.getSimage());
	        existingState.setIpAddress(stateDetails.getIpAddress());
	        existingState.setStatus(stateDetails.isStatus());
	        existingState.setCreated_date(stateDetails.getCreated_date());
	        existingState.setModified_date(stateDetails.getModified_date());
	        existingState.setCreated_by(stateDetails.getCreated_by());
	        existingState.setModified_by(stateDetails.getModified_by());
	        existingState.setIsdelete(stateDetails.isIsdelete());
	        
	        return stateRepository.save(existingState);
	    }
 
		
		public State getStateById(Long id) {
	        return stateRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Country not found"));
	    }
	    
	    
	    
//	    public State updateState(Long id, State stateDetails) {
//	        Optional<State> optionalState = stateRepository.findById(id);
//	        if (optionalState.isPresent()) {
//	            State state = optionalState.get();
//	            state.setStateName(stateDetails.getStateName());
//	            state.setCode(stateDetails.getCode());
//	            state.setS_image(stateDetails.getS_image());
//	            state.setIpAddress(stateDetails.getIpAddress());
//	            state.setCountry(stateDetails.getCountry());
//	            return stateRepository.save(state);
//	        } else {
//	            throw new RuntimeException("State not found with id: " + id);
//	        }
//	    }

	    public void deleteState(Long id) {
	        stateRepository.deleteById(id);
	    }

//		public List<State> getStateByCountryId(Long cid) {
//			return stateRepository.findByCountryId(cid);
//		}
		  public CountryStateResponse  getStateByCountryId(Long cid) {
		        List<State> state=stateRepository.findByCountryId(cid);
		        long totalStates = stateRepository.countByCountryId(cid);
		   return new CountryStateResponse(state, totalStates);
		    
		}
}
