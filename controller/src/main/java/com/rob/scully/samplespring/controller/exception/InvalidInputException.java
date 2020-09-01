package com.rob.scully.samplespring.controller.exception;

/**
 * The Input was Invalid exception.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Instantiates a new Invalid Input Exception.
     *
     * @param message the message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
