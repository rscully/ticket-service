package com.rob.scully.samplespring.controller.helper;

import com.rob.scully.samplespring.controller.exception.BindingResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * A controller helper for binding results
 *
 * @author rscully 31/08/2020
 */
public class ControllerHelpers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelpers.class);

    /**
     * The checks the binding result and throws a BindingResultException if one occurred.
     *
     * @param bindingResult the bindingResult being checked
     */
    public static void checkBinding(BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            LOGGER.error("{} Binding Error(s) : {} ", bindingResult.getErrorCount(), bindingResult.getAllErrors());
            String message = getBindingErrorMessage(bindingResult.getFieldErrors());
            throw new BindingResultException(message);
        }
    }

    /**
     * In the event of a BindingResult error we create a String containing all the FieldErrors in the Bind
     *
     * @param fieldErrors The list of field errors
     * @return string representation of all the field errors
     */
    private static String getBindingErrorMessage(List<FieldError> fieldErrors)
    {
        String error = "";

        for(FieldError fieldError : fieldErrors)
        {
            error += fieldError.getField() +" " +fieldError.getDefaultMessage() + ". ";
        }

        return error;
    }

}
