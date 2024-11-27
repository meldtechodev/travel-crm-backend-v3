package com.MotherSon.CRM.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MotherSon.CRM.models.Season;
import com.MotherSon.CRM.repository.SeasonRepository;

@Service
public class SeasonService {
	
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	
	
	
	public Season addSeason(Season season) {
		return seasonRepository.save(season);
	}
	
	
	public List<Season> getAllSeason() {
		return seasonRepository.findAll();
	}

}
