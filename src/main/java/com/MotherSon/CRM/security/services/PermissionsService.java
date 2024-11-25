package com.MotherSon.CRM.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Permissions;
import com.MotherSon.CRM.repository.PermissionsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermissionsService {
	
	
	@Autowired
	private PermissionsRepository permissionsRepository;
	
	
	public Permissions addPermissions(Permissions permissions)
		{
			Permissions per = permissionsRepository.save(permissions);
			return per;
			
		}
		
		
		public boolean existsByPermissionName(String permissionName) {
	        return permissionsRepository.existsByPermissionName(permissionName);
	    }


		public Optional<Permissions> getPermissionsById(Long id){
			return permissionsRepository.findById(id);
		}


		public Permissions getPermissionById(Long id) {
	        return permissionsRepository.findById(id).orElse(null); // Return the Permission or null if not found
	    }
		
		
//		public Optional<Permissions> getPermissionsById(Long id) {
//	        return permissionsRepository.findById(id);
//	    }
//	}
		public List<Permissions> getAllPermissions(){
			List<Permissions> allpermision = permissionsRepository.findAll();
			return allpermision;
		}
		
		
		
		public void deleteById(Long id) {
			Optional<Permissions> existingPermissionopt = permissionsRepository.findById(id);
			if(existingPermissionopt.isPresent())
			{
				permissionsRepository.deleteById(id);
			}
			else
			{
				throw new EntityNotFoundException(" Permission with this ID " + id + " Not Found ");
			}
		}
}
	


	


