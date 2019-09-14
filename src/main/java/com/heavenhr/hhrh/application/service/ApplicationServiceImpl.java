package com.heavenhr.hhrh.application.service;

import com.heavenhr.hhrh.application.model.Application;
import com.heavenhr.hhrh.notification.service.NotificationService;
import com.heavenhr.hhrh.application.repo.ApplicationRepository;
import com.heavenhr.hhrh.offer.service.OfferService;
import com.heavenhr.hhrh.utils.CacheManager;
import com.heavenhr.hhrh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private OfferService offerService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Application addApplication(Application application) {

        application.setDate(LocalDate.now());
        application.setUpdate(LocalDate.now());
        application.setEmailId(application.getEmailId().toLowerCase());
        application.setStatus(Constants.ApplicationStatus.APPLIED.toString());
        offerService.updateApplicantCount(application.getOfferId());
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplicationStatus(Long applicationId, String status) {

        Application applicationToUpdate = applicationRepository.findById(applicationId).get();

        applicationToUpdate.setStatus(status);
        applicationToUpdate.setUpdate(LocalDate.now());
        applicationRepository.save(applicationToUpdate);
        notificationService.notify(applicationToUpdate);
        return applicationToUpdate;
    }

    @Override
    public Application getApplication(Long offerId, String emailId) {

        Application application = applicationRepository.findApplication(offerId, emailId);
        if (null != application) {
            application.setJobTitle(CacheManager.getOfferTitles().get(offerId));
        }
        return application;
    }

    @Override
    public List<Application> getApplications() {

        return addJobTitles(applicationRepository.findAll());
    }

    @Override
    public List<Application> findApplicationsByOfferId(Long offerId) {

        List<Application> applications = applicationRepository.findApplicationsByOfferId(offerId);
        addJobTitles(applications);
        return applications;
    }

    @Override
    public Application findApplications(Long id) {

        return applicationRepository.findById(id).get();
    }

    @Override
    public List<Application> findApplications(String emailId) {

        return applicationRepository.findApplications(emailId.toLowerCase());
    }

    private List<Application> addJobTitles(List<Application> applications) {
        applications.forEach(a ->
                a.setJobTitle(CacheManager.getOfferTitles().get(a.getOfferId().intValue())));
        return applications;
    }
}
