package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Transport;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Long>
{

}
