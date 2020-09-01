package com.rob.scully.samplespring.controller.exception;

/**
 * The type Binding not found not found exception.
 */
public class BindingResultException extends RuntimeException {

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public BindingResultException(String message) {
        super(message);
    }
}
