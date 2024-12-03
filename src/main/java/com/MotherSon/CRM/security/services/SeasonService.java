package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Modules;
import com.MotherSon.CRM.models.RoomTypes;
import com.MotherSon.CRM.models.Season;
import com.MotherSon.CRM.repository.SeasonRepository;

@Service
public class SeasonService {
	
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	
	
	
//	public Season addSeason(Season season) {
//		return seasonRepository.save(season);
//	}
	
	
	
	public Season addSeason(Season season) {
		if(seasonRepository.existsBySeasonName(season.getSeasonName())) {
			throw new IllegalArgumentException(" Season Name With This Name " + season.getSeasonName() + "already exists.");
		}
		
		return seasonRepository.save(season);
	}
	
	

//	public List<Season> getAllSeason() {
//		return seasonRepository.findAll();
//	}
	
	
	public Page<Season> getSeason(int page , int size , String sortDirection){
		Sort sort = Sort.by(Sort.Order.asc("seasonName"));
		
		if("desc".equalsIgnoreCase(sortDirection)) {
			sort =  Sort.by(Sort.Order.desc("seasonName"));
		}
		
		PageRequest  pageable = PageRequest.of(page, size, sort);
		return seasonRepository.findAll(pageable);
		}

}
