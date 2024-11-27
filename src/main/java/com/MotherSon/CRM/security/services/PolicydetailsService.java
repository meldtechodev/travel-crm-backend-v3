package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<PolicyDetails> getallpolicydetailsser()
	{
		  List<PolicyDetails> getpolicydetai=policydetailsrepository.findAll();
		   return getpolicydetai;
		
	}
}
