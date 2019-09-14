package com.heavenhr.hhrh.application.controller;

import com.heavenhr.hhrh.application.model.Application;
import com.heavenhr.hhrh.application.service.ApplicationService;
import com.heavenhr.hhrh.exceptions.ValidationException;
import com.heavenhr.hhrh.model.Response;
import com.heavenhr.hhrh.utils.Constants;
import com.heavenhr.hhrh.utils.ControllerUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Api(value = "Application REST APIs")
@Slf4j
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @ApiOperation(value = "Apply to a Job Offer")
    @PostMapping("/v1")
    public Response applyToOffer(@RequestBody Application application) {

        Response response = new Response();
        try {
            validateApplicationRequest(application);
            response.setData(applicationService.addApplication(application));
        } catch (ValidationException e) {
            log.info("Invalid Job Application {}", e.getMessage());
            return ControllerUtility.setBadRequestError(response, e.getMessage());
        } catch (Exception e) {
            log.error("Exception occurred while applying for new Job Offer {}", e);
            return ControllerUtility.setInternalServerError(e.getMessage());
        }
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Get a application by Id")
    @GetMapping("v1/{id}")
    public Response getApplication(@PathVariable("id") Long id) {

        Response response = new Response();

        try {
            Application application = applicationService.findApplications(id);
            response.setData(application);
            return ControllerUtility.setSuccessResponse(response);
        } catch (NoSuchElementException nse) {
            return ControllerUtility.setBadRequestError(response, "Application Id doesn't exist - " + id);
        }
    }

    @ApiOperation(value = "Get All the Applications")
    @GetMapping("v1")
    public Response getApplications() {

        Response response = new Response();

        response.setData(applicationService.getApplications());
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Query Applications by email Id")
    @GetMapping("v1/query")
    public Response queryApplications(@RequestParam("emailId") String emailId) {

        Response response = new Response();
        List<Application> application = applicationService.findApplications(emailId);
        response.setData(application);
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Query all Applications by offer Id")
    @GetMapping("v1/queryOffer")
    public Response queryApplications(@RequestParam("offerId") Long offerId) {

        Response response = new Response();
        List<Application> application = applicationService.findApplicationsByOfferId(offerId);
        response.setData(application);
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Update the status of an application")
    @PatchMapping("/v1/{id}/")
    public Response updateStatus(@PathVariable(value = "id") Long applicationId,
                                 @RequestParam(value = "status") String status) {

        Response response = new Response();
        Constants.ApplicationStatus applicationStatus;
        status = status.toLowerCase();
        status = StringUtils.capitalize(status);
        try {
            Constants.ApplicationStatus.valueOf(status.toUpperCase());
            Application application =
                    applicationService.updateApplicationStatus(applicationId, status);
            response.setData(application);
        } catch (IllegalArgumentException ex) {
            return ControllerUtility.setBadRequestError(response, "Invalid Status");
        } catch (NoSuchElementException nse) {
            return ControllerUtility.setBadRequestError(response, "Offer doesn't exist " + applicationId);
        } catch (Exception e) {
            return ControllerUtility.setInternalServerError(e.getMessage());
        }
        return ControllerUtility.setSuccessResponse(response);
    }

    /**
     * class to validate Application parameters
     *
     * @param application
     * @return
     * @throws ValidationException
     */
    boolean validateApplicationRequest(Application application) throws ValidationException {

        if (StringUtils.isBlank(application.getEmailId())) {
            throw new ValidationException("Email ID cannot be blank");
        }

        // TODO validation to check the offer ID

        application.setEmailId(application.getEmailId().toLowerCase());
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(application.getEmailId())) {
            throw new ValidationException("Invalid email ID : " + application.getEmailId());
        }

        if (null != applicationService.getApplication(application.getOfferId(), application.getEmailId())) {
            throw new ValidationException("You've already apllied for this posistion");
        }

        return true;
    }


}

