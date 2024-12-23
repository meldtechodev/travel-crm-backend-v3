package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Activities;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

	Activities findByTitle(String title);

}
