package com.rob.scully.samplespring.service;

import com.rob.scully.samplespring.domain.Ticket;

public interface TicketService {

    /**
     * Retrieves a ticket via its id
     *
     * @param  - ticketId - the id of the ticket we are retrieving
     * @return - the retrieved ticket
     */
    Ticket getTicketById(String ticketId);

    /**
     * Creates a new ticket with a fine value
     *
     * @param ticket - the ticket we are creating
     * @return - the new ticket that has been created
     */
    Ticket createNewTicket(Ticket ticket);

    /**
     * Updates a ticket status to paid
     * @param ticket - the ticket being paid
     * @return - the newly updated ticket object
     */
    Ticket updateTicketStatusToPaid(Ticket ticket);
}
