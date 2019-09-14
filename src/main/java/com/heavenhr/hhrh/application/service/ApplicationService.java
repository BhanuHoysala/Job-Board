package com.heavenhr.hhrh.application.service;

import com.heavenhr.hhrh.application.model.Application;

import java.util.List;

public interface ApplicationService {

    Application addApplication(Application application);

    Application findApplications(Long id);

    List<Application> findApplications(String emailId);

    Application updateApplicationStatus(Long applicationId, String status);

    Application getApplication(Long offerId, String emailId);

    List<Application> getApplications();

    List<Application> findApplicationsByOfferId(Long offerId);
}
