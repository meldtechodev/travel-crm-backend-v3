package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Policy;
import com.MotherSon.CRM.models.PolicyDetails;
import com.MotherSon.CRM.repository.PolicydetailsRepository;

@Service
public class PolicydetailsService {
    @Autowired
    private PolicydetailsRepository policydetailsrepository;
	public PolicyDetails createpolicydetailsser(PolicyDetails policydetails)
	{
		PolicyDetails savepolicydetails=policydetailsrepository.save(policydetails);
		return savepolicydetails;
		
	}
//	public List<PolicyDetails> getallpolicydetailsser()
//	{
//		  List<PolicyDetails> getpolicydetai=policydetailsrepository.findAll();
//		   return getpolicydetai;
//		
//	}
	
	
	
	public Page<PolicyDetails> getPolicyDetails(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("policytitle"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort =  Sort.by(Sort.Order.desc("policytitle"));
		}
		
		PageRequest  pageable = PageRequest.of(page, size, sort);
		return policydetailsrepository.findAll(pageable);
		}
}
