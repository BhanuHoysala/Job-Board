package com.heavenhr.hhrh.offer.service;

import com.heavenhr.hhrh.offer.model.Offer;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferServiceTest {

    @Autowired
    private OfferService offerService;

    @Test
    public void t1_add_new_job_Offer() {

        Offer offer = new Offer();
        offer.setJobTitle("Kotlin Developer");
        offer.setStartDate(LocalDate.now());
        offerService.addOffer(offer);

        List<Offer> persistedObject = offerService.findOffer("Kotlin Developer");
        Assert.assertNotNull(offerService.findOffer("Kotlin"));
        Assert.assertEquals(0, persistedObject.get(0).getApplicantsCount());
        Assert.assertEquals("Kotlin Developer", persistedObject.get(0).getJobTitle());
    }

    /**
     * fetch a single offer by ID
     */
    @Test
    public void t2_fetch_a_job_offer_by_id() {

        Assert.assertNotNull(offerService.findOffer(1l));
    }

    /**
     * negative scenarios when invalid offer id passed
     */
    @Test(expected = NoSuchElementException.class)
    public void t3_when_try_to_fetch_offer_with_invalid_id() {

        offerService.findOffer(99l);
    }
}