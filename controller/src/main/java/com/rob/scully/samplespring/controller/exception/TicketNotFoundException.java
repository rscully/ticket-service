package com.rob.scully.samplespring.controller.exception;

public class TicketNotFoundException extends Exception {

    /**
     * Instantiates a new Ticket not found exception
     *
     * @param message the message
     */
    public TicketNotFoundException(String message) {
        super(message);
    }
}
