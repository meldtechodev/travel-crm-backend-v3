package com.MotherSon.CRM.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
 
import com.MotherSon.CRM.models.PaymentLinks;
 
@Repository

public interface PaymentLinksRepository extends JpaRepository<PaymentLinks, Long> {
 
}
