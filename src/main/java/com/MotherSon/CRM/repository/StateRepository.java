package com.MotherSon.CRM.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long>

{
	
//	Optional<State> findByMasterKey(String masterKey);

	List<State> findByCountryId(Long country_id);
	
     boolean existsByStateName(String stateName);
	//State findByMasterkey(String masterkey);
	
}


