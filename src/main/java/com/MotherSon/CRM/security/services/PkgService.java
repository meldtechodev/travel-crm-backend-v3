package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Pkg;
import com.MotherSon.CRM.repository.PkgRepository;

@Service
public class PkgService {
		
		
@Autowired
private PkgRepository pkgRepository;
		
		
		public Pkg addPkg(Pkg pkg) {
			return pkgRepository.save(pkg);
		}
		
		
		public Optional<Pkg> getPkgsById(Long id) {
	        return pkgRepository.findById(id);
	    }
		
		
		public List<Pkg> getAllPkg() {
			return pkgRepository.findAll();
		}
		
		
		
		
		
		public Pkg updatePkgById(Long id, Pkg pkgDetails) {
	        Pkg existingPkg = pkgRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Hotl not found"));

	        existingPkg.setPkName(pkgDetails.getPkName());
	        //existingPkg.setFromCityid(pkgDetails.getFromCityid());
	        //existingPkg.setToCityid(pkgDetails.getToCityid());
	       // existingPkg.setCityCoverdid(pkgDetails.getCityCoverdid());
	        existingPkg.setDescription(pkgDetails.getDescription());
	        existingPkg.setPkCategory(pkgDetails.getPkCategory());
	        existingPkg.setPkSpecifications(pkgDetails.getPkSpecifications());
	        existingPkg.setPimage(pkgDetails.getPimage());
	        existingPkg.setDays(pkgDetails.getDays());
	        existingPkg.setNights(pkgDetails.getNights());
	        existingPkg.setIs_fixed_departure(pkgDetails.isIs_fixed_departure());
	        existingPkg.setFixed_departure_destinations(pkgDetails.getFixed_departure_destinations());
	        existingPkg.setPackageType(pkgDetails.getPackageType());
	        existingPkg.setCreated_by(pkgDetails.getCreated_by());
	        existingPkg.setModified_by(pkgDetails.getModified_by());
	        existingPkg.setIpaddress(pkgDetails.getIpaddress());
	        existingPkg.setStatus(pkgDetails.isStatus());
	        existingPkg.setIsdelete(pkgDetails.isIsdelete());
	        existingPkg.setCreated_date(pkgDetails.getCreated_date());
	        existingPkg.setModified_date(pkgDetails.getModified_date());
	        
	        return pkgRepository.save(existingPkg);
	    }

		
		public Pkg getPkgById(Long id) {
	        return pkgRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Destination not found"));
	    }


	public Pkg findById(long id) {
		return null;
	}
	public void deleteById(long id) {
		pkgRepository.deleteById(id);
		
	}}
