package com.heavenhr.hhrh.utils;

import com.heavenhr.hhrh.model.Meta;
import com.heavenhr.hhrh.model.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public interface ControllerUtility {

    static boolean validatePhoneNumber(String phoneNo) {

        if (StringUtils.isBlank(phoneNo) || phoneNo.length() != 10) {
            return false;
        }
        // validate phone numbers of format "1234567890"
        if (!phoneNo.matches("\\d{10}"))
            return false;
		/*// validating phone number with -, . or spaces
		else if (!phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return false;
		// validating phone number with extension length from 3 to 5
		else if (!phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return false;
		// validating phone number where area httpStatusCode is in braces ()
		else if (!phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return false;
		// return false if nothing matches the input
*/
        else
            return true;

    }

    static Response setSuccessResponse(Response response) {

        response.setMeta(new Meta(HttpStatus.OK.value(), HttpStatus.OK.name(), ""));
        return response;
    }

    static Response setInternalServerError(String message) {

        Response response = new Response();
        response.setMeta(new Meta(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(), message));
        return response;
    }

    static Response setBadRequestError(Response response, String message) {

        response.setMeta(new Meta(HttpStatus.BAD_REQUEST.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(), message));
        return response;
    }
}
