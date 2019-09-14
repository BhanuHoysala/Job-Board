package com.heavenhr.hhrh.offer.service;

import com.heavenhr.hhrh.offer.model.Offer;

import java.util.List;

public interface OfferService {

    Offer addOffer(Offer offer);

    Offer findOffer(Long offerId);

    List<Offer> findOffers();

    List<Offer> findOffer(String jobTitle);

    Offer updateApplicantCount(Long offerId);
}
