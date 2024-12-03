package com.MotherSon.CRM.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.models.Policy;
import com.MotherSon.CRM.repository.PolicyRepository;

@Service
public class PolicyService {
	
	
	@Autowired
	  private PolicyRepository policyRepository;

	  public Policy createpolicy(Policy policy)
	  {
		//  policy.setLastUpdated(LocalDateTime.now());
		  policy.setModifiedDate(LocalDateTime.now());

		          Policy policyrepo =  policyRepository.save(policy);
		  
		return policyrepo;
		  
	  }
	  
	  public Optional<Policy> getpolicyById(Long id)
	  {
		  
		return policyRepository.findById(id);
		       
		     
	 }
//	  public List<Policy> getallpolicyser()
//	  {
//		List<Policy>getpolicyre=      policyRepository.findAll();
//		
//		return getpolicyre;
//		  
//	  }
	  
	  
	  public Page<Policy> getPolicy(int page , int size , String sortDirection){
			Sort sort = Sort.by(Sort.Order.asc("pkName"));
			
			if("desc".equalsIgnoreCase(sortDirection)) {
				sort =  Sort.by(Sort.Order.desc("pkName"));
			}
			
			PageRequest  pageable = PageRequest.of(page, size, sort);
			return policyRepository.findAll(pageable);
			}
	  
	  
	  
	  
	  public Policy updatepolicy(Long id ,Policy policyDetails)
	  {
		  //  Optional<Policy> policy=  policyrepository.findById(id);
		  
		   return policyRepository.save(policyDetails);
		  
	  }
	  public void Deletebyid( Long id)
	  {
		 policyRepository.deleteById(id); 
	  }
}