package com.rob.scully.samplespring.controller.resource;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * This will be a list of Error codes for the application
 */
@BaseName("error")
@LocaleData( { @Locale("en")})
public enum ErrorCodes
{
        TICKET_NOT_FOUND(101),
        INVALID_INPUT_PARAMETER(102),
    BINDING_ERROR(103);

        private int code;

        ErrorCodes(int code) {
            this.code = code;
        }

        /**
         * Gets the Error status code
         * @return the status code number
         */
        public int getCode() {
            return code;
        }

}
