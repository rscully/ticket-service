package com.rob.scully.samplespring.controller.advice;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import com.rob.scully.samplespring.controller.exception.BindingResultException;
import com.rob.scully.samplespring.controller.exception.InvalidInputException;
import com.rob.scully.samplespring.controller.exception.TicketNotFoundException;
import com.rob.scully.samplespring.controller.resource.ErrorCodes;
import com.rob.scully.samplespring.controller.resource.ErrorResource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author rscully 28/08/2020
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TicketNotFoundException.class)
    ResponseEntity<Object> handleTicketNotFoundException(HttpServletRequest request, TicketNotFoundException e) {

        return handleException(
                request, ErrorCodes.TICKET_NOT_FOUND, NOT_FOUND
        );

    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    ResponseEntity<Object> handleInputInvalidException(HttpServletRequest request, InvalidInputException e) {

        return handleException(
                request, ErrorCodes.INVALID_INPUT_PARAMETER, BAD_REQUEST
        );
    }

    @ExceptionHandler(BindingResultException.class)
    ResponseEntity<Object> handleBindingResultException(HttpServletRequest request, BindingResultException e) {
        return handleException(
                request, ErrorCodes.BINDING_ERROR, BAD_REQUEST
        );
    }

    private ResponseEntity<Object> handleException(HttpServletRequest request, ErrorCodes errorCode, HttpStatus status) {

        final IMessageConveyor mc = new MessageConveyor(LocaleContextHolder.getLocale());
        final String message = mc.getMessage(errorCode);
        ErrorResource errorResource = new ErrorResource();
        errorResource.setErrorCode(errorCode.getCode());
        errorResource.setMessage(message);
        errorResource.setHttpStatus(status.value());
        errorResource.setRequestUrl(request.getRequestURL().toString());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        errorResource.setExceptionTime(date);
        errorResource.setErrorMessage(status.getReasonPhrase());

        return new ResponseEntity<>(errorResource, status);
    }
}