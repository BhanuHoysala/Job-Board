package com.heavenhr.hhrh.offer.controller;

import com.heavenhr.hhrh.model.Response;
import com.heavenhr.hhrh.offer.model.Offer;
import com.heavenhr.hhrh.offer.service.OfferService;
import com.heavenhr.hhrh.utils.ControllerUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Offer REST APIs")
@Slf4j
@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @ApiOperation(value = "Get all the Job offer postings")
    @GetMapping("v1")
    public Response getJobOffers() {

        Response response = new Response();

        List<Offer> offer = offerService.findOffers();
        response.setData(offer);
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Get a Job offer posting")
    @GetMapping("v1/{id}")
    public Response getJobOffer(@PathVariable("id") Long id) {

        Response response = new Response();

        Offer offer = offerService.findOffer(id);
        response.setData(offer);
        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Post new Job offer")
    @PostMapping("/v1")
    public Response addOffer(@RequestBody Offer offer) {

        Response response = new Response();
        try {
            /*  TODO before sending to persistence to persistence layer
                1. validate the start date by by receiving through DTO
                2. validate the new Job Offer is not exist with any of the status except "Hired"
                3. Condense the data fields
             */
            response.setData(offerService.addOffer(offer));
        } catch (Exception e) {
            log.error("Exception occurred while adding new Job Offer ", e);
            return ControllerUtility.setInternalServerError(e.getMessage());
        }

        return ControllerUtility.setSuccessResponse(response);
    }

    @ApiOperation(value = "Query job offers by title Ex: 'Java' ")
    @GetMapping("/v1/query")
    public Response getQueryJobOffer(@RequestParam("jobTitle") String jobTitle) {

        Response response = new Response();
        List<Offer> offer = offerService.findOffer(jobTitle);
        response.setData(offer);
        return ControllerUtility.setSuccessResponse(response);
    }
}
