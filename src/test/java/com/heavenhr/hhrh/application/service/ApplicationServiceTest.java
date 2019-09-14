package com.heavenhr.hhrh.application.service;

import com.heavenhr.hhrh.application.model.Application;
import com.heavenhr.hhrh.offer.model.Offer;
import com.heavenhr.hhrh.offer.service.OfferService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private OfferService offerService;

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void t11_addApplication() {

        // Initially applicants are empty
        Assert.assertTrue(CollectionUtils.isEmpty(offerService.findOffers()));

        Offer nodeJsOffer = new Offer();
        nodeJsOffer.setJobTitle("NodeJS Developer");
        nodeJsOffer.setStartDate(LocalDate.now());
        nodeJsOffer = offerService.addOffer(nodeJsOffer);

        Application application = new Application();
        application.setOfferId(nodeJsOffer.getId());
        application.setEmailId("bhanu1@gmail.com");
        application.setStatus("Applied");
        application.setDate(LocalDate.now());
        applicationService.addApplication(application);

        // verifying Application Applied
        Assert.assertNotNull(applicationService.findApplications("bhanu1@gmail.com"));

        // verifying Application counter increased
        Assert.assertEquals(
                offerService.findOffer("NodeJS Developer").get(0).getApplicantsCount(), 1);
    }

    /**
     * Verifying Application status update
     */
    @Test
    public void t12_updateApplicationStatus() {

        Application applicationBeforeStatusUpdate = applicationService.getApplications().get(0);
        Application applicationAfterStatusUpdate =
                applicationService.updateApplicationStatus(applicationBeforeStatusUpdate.getId(), "Hired");
        Assert.assertEquals("Hired", applicationAfterStatusUpdate.getStatus());
    }

    /**
     * verifying single application fetch
     */
    @Test
    public void t13_getApplication() {

        Assert.assertNotNull(applicationService.findApplications(1l));
    }

    /**
     * negative scenarios when invalid application id passed
     */
    @Test(expected = NoSuchElementException.class)
    public void t14_getApplication() {

        Assert.assertNotNull(applicationService.findApplications(99l));
    }

}