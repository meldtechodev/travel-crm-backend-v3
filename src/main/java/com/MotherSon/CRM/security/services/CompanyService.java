package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {
	@Autowired
	 private CompanyRepository companyrepository;
 
	public Company createcompanyser(Company company)
	{
		   Company companysave= companyrepository.save(company);
		
		return companysave ;
		
	}
	
//	public List< Company> getallcompanyser()
//	{
//		       List<Company>getallcom=companyrepository.findAll();
//	      	return getallcom;
//		
//	}
	
	
	
	public Page<Company> getCompany(int page, int size, String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("companyname"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort = Sort.by(Sort.Order.desc("companyname"));
		}
		
		PageRequest pageable = PageRequest.of(page, size, sort);
		return companyrepository.findAll(pageable);
			
		}
	
	
	
	
	
	
	public Optional<Company> getcompanybyid(Long id)
	{
		 Optional<Company> getcompanybyid=companyrepository.findById(id);
		return getcompanybyid;
		  
		
	}
	
	public Company updatebyid(Company company,Long id)
	{
		 Optional<Company> companyid=companyrepository.findById(id);
		
		 if(companyid.isPresent())
		 {
			   Company existcompany= companyid.get();
			   existcompany.setCompanyname(company.getCompanyname());
			   existcompany.setCompanyaddress(company.getCompanyaddress());
			   existcompany.setCompanycountrycode(company.getCompanycountrycode());
			   existcompany.setCompanyphone(company.getCompanyphone());
			   existcompany.setCompanyemail(company.getCompanyemail());
			   existcompany.setCompanywebsite(company.getCompanywebsite());
			   existcompany.setCreatedby(company.getCreatedby());
			   existcompany.setCompanylogo(company.getCompanylogo());
			   
			   existcompany.setStatus(company.isStatus());
			   existcompany.setModifiedby(company.getModifiedby());
			   existcompany.setModifieddate(company.getModifieddate());
			   existcompany.setIpaddress(company.getIpaddress());
			   existcompany.setIsdelete(company.isIsdelete());
			   existcompany.setParent_id(company.getParent_id());
			   
			   return companyrepository.save(existcompany);
			   
		 }
		 else {
		        // Handle case where company is not found
		        throw new EntityNotFoundException("Company with ID " + id + " not found.");
		    }
		
		
	}
	
	
	public void deleteById(Long id) {
	    // Check if the company exists
	    Optional<Company> existingCompanyOptional = companyrepository.findById(id);

	    if (existingCompanyOptional.isPresent()) {
	        // Delete the company if it exists
	        companyrepository.deleteById(id);
	        
	    } else {
	        // Handle case where company is not found
	        throw new EntityNotFoundException("Company with ID " + id + " not found.");
	    }
	}

	public Company getcompanysbyid(Long id) {
		// TODO Auto-generated method stub
		Optional<Company>compid=companyrepository.findById(id);
		if(compid.isPresent())
		{
		 Company complogo=	compid.get();
		 return complogo;
		}
		else
		{
	        // Handle case where company is not found
	        throw new EntityNotFoundException("Company with ID " + id + " not found.");
	    }
	}

	
	
	
	
	
}
