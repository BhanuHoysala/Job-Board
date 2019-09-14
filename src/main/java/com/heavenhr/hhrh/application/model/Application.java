package com.heavenhr.hhrh.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {

//    private static final long serialVersionUID = -6207501405073639484L;

    @ApiModelProperty(notes = "ApplicationId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "ApplicationId", required = true)
    private Long offerId;

    @Transient
    private String jobTitle;

    @ApiModelProperty(notes = "Candidate email ID", required = true)
    private String emailId;

    @ApiModelProperty(notes = "Resume in text")
    private String resume;

    @ApiModelProperty(notes = "Status of the application")
    private String status; // (APPLIED, INVITED, REJECTED, HIRED)

    @ApiModelProperty(notes = "Apply date")
    private LocalDate date;

    @ApiModelProperty(notes = "Status update date")
    private LocalDate update;

}
