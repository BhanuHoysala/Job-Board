package com.heavenhr.hhrh.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Constants {

    enum ApplicationStatus {

        @JsonProperty("Applied")
        APPLIED("Applied"),
        @JsonProperty("Invited")
        INVITED("Invited"),
        @JsonProperty("Rejected")
        REJECTED("Rejected"),
        @JsonProperty("Hired")
        HIRED("Hired");

        private final String status;

        /**
         * @param status
         */
        ApplicationStatus(final String status) {
            this.status = status;
        }

        /**
         *
         * @return String
         */
        @Override
        public String toString() {
            return status;
        }
    }
}
