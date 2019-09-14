package com.heavenhr.hhrh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="Heaven HR Hiring API Service Response")
public class Response {

    @ApiModelProperty(notes = "Response Meta data", required = true)
    private Meta meta;

    @ApiModelProperty(notes = "Data")
    private Object data;
}
