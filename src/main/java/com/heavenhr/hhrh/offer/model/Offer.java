package com.heavenhr.hhrh.offer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {

    @ApiModelProperty(notes = "Offer Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Job Title", required = true)
    private String jobTitle;

    @ApiModelProperty(notes = "Offer posted Date", required = true)
    private LocalDate startDate;

    @ApiModelProperty(notes = "Applicants count")
    private int applicantsCount;
}
