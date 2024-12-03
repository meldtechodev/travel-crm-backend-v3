package com.MotherSon.CRM.security.services;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.repository.ModulesRepository;
 
import jakarta.persistence.EntityNotFoundException;
 
@Service
public class ModulesService {
	
	
	@Autowired
	private ModulesRepository modulesRepository;
	
//	public Modules addModules(Modules modules) {
//		Modules admodule = modulesRepository.save(modules);
//		return admodule;
//	}
	
	
	
	
	public Modules addModules(Modules modules) {
		if(modulesRepository.existsByModuleName(modules.getModuleName())) {
			throw new IllegalArgumentException("Modules With This Name" + modules.getModuleName() + "already exists.");
		}
		
		return modulesRepository.save(modules);
	}
	
	
	
	
	public List<Modules> getAllModules(){
		List<Modules> allmodule = modulesRepository.findAll();
		return allmodule;
	}
	
	
	
 
	public Optional<Modules> getModulesById(Long id){
		return modulesRepository.findById(id);
	}
	
	public Modules getModuleById(Long id) {
        Optional<Modules> module = modulesRepository.findById(id);
        return module.orElse(null);  
    }

	public Modules updateModule(Long id, Modules updatedModule) {
        Optional<Modules> existingModule = modulesRepository.findById(id);
        
        if (!existingModule.isPresent()) {
            return null;
        }
        
        Modules module = existingModule.get();
        
        // Update the fields with the new values
        module.setModuleName(updatedModule.getModuleName());
        module.setParentId(updatedModule.getParentId());
        module.setCreatedBy(updatedModule.getCreatedBy());
        module.setModifiedBy(updatedModule.getModifiedBy());
        module.setIpaddress(updatedModule.getIpaddress());
        module.setStatus(updatedModule.isStatus());
        module.setIsdelete(updatedModule.isIsdelete());
        module.setPermissions(updatedModule.getPermissions()); // Update permissions if needed
        
        // Save the updated module
        return modulesRepository.save(module);
    }
	
	
	
	public void deleteById(Long id) {
		Optional<Modules> existingmodule = modulesRepository.findById(id);
		if(existingmodule.isPresent()) {
			modulesRepository.deleteById(id);
		}
		else
		{
			throw new EntityNotFoundException(" Module with this ID" + id + " Not Found ");
		}
		
	}
	
	
}
 
 
 