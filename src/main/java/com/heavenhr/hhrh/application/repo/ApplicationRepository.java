package com.heavenhr.hhrh.application.repo;

import com.heavenhr.hhrh.application.model.Application;
import com.heavenhr.hhrh.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a WHERE a.offerId = ?1 AND a.emailId = ?2")
    Application findApplication(Long offerId, String emailId);

    @Query("SELECT a FROM Application a WHERE a.emailId LIKE %?1%")
    List<Application> findApplications(String emailId);

    @Query("SELECT a FROM Application a WHERE a.offerId = ?1")
    List<Application> findApplicationsByOfferId(Long offerId);
}

