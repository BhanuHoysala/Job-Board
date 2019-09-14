package com.heavenhr.hhrh.offer.service;

import com.heavenhr.hhrh.offer.model.Offer;
import com.heavenhr.hhrh.offer.repo.OfferRepository;
import com.heavenhr.hhrh.utils.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Offer addOffer(Offer offer) {

        if (null == offer.getStartDate()) {
            offer.setStartDate(LocalDate.now());
        }
        Offer offerEntity = offerRepository.save(offer);

        cacheManager.getOfferTitles().put(offerEntity.getId().intValue(),
                offerEntity.getJobTitle());
        return offerEntity;
    }

    @Override
    public Offer findOffer(Long offerId) {

        return offerRepository.findById(offerId).get();
    }

    @Override
    public List<Offer> findOffer(String jobTitle) {

        return offerRepository.findJobOffers(jobTitle);
    }

    @Override
    public Offer updateApplicantCount(Long offerId) {
        Offer offer = offerRepository.findById(offerId).get();
        offer.setApplicantsCount(offer.getApplicantsCount() + 1);
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> findOffers() {
        return offerRepository.findAll();
    }
}
