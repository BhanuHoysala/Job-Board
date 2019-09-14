package com.heavenhr.hhrh.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meta {

    @ApiModelProperty(notes = "HTTP response code", required = true)
    private int code;

    /**
     * Generic HTTP message for corresponding HTTP code
     */
    @ApiModelProperty(notes = "HTTP status message", required = true)
    private String status;

    /**
     * TO give the precise message about the error
     */
    @ApiModelProperty(notes = "Server response message")
    private String message;

}
