package com.heavenhr.hhrh.config;

import com.heavenhr.hhrh.model.Response;
import com.heavenhr.hhrh.utils.ControllerUtility;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) {

        return ControllerUtility.setInternalServerError(e.getMessage());
    }
}
