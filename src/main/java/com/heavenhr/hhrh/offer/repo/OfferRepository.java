package com.heavenhr.hhrh.offer.repo;

import com.heavenhr.hhrh.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("SELECT o FROM Offer o WHERE o.jobTitle LIKE %?1%")
    List<Offer> findJobOffers(String jobTitle);
}
