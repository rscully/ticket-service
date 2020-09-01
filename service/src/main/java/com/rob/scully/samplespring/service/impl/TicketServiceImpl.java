package com.rob.scully.samplespring.service.impl;

import com.rob.scully.samplespring.dao.TicketRepository;
import com.rob.scully.samplespring.domain.Ticket;
import com.rob.scully.samplespring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    /**
     *
     * @param ticketRepository
     */
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository)
    {
        this.ticketRepository= ticketRepository;
    }

    /**
     * Retrieves a ticket via its id
     * @param ticketId - the id of the ticket we are retrieving
     * @return the retrieved ticket
     */
    public Ticket getTicketById(String ticketId)
    {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if(optionalTicket.isPresent())
        {
            return optionalTicket.get();
        }
        else
        {
            return null;
        }

    }

    /**
     * Creates a new ticket with a fine value
     *
     * @param ticket - the ticket we are creating
     * @return - the new ticket that has been created
     */
    public Ticket createNewTicket(Ticket ticket)
    {
        return ticketRepository.save(ticket);
    }

    /*
     * Updates a ticket status to paid
     * @param ticket - the ticket being paid
     * @return - the newly updated ticket object
     */
    public Ticket updateTicketStatusToPaid(Ticket ticket)
    {
        ticket.setPaid(true);
        return ticketRepository.save(ticket);
    }
}
