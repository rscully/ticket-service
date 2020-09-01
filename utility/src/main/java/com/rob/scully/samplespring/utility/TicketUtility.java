package com.rob.scully.samplespring.utility;

import com.rob.scully.samplespring.domain.Ticket;
import com.rob.scully.samplespring.dto.TicketDto;

import java.math.BigDecimal;

public final class TicketUtility {

    public static TicketDto mapDomainToTicketDto(Ticket ticket)
    {
        TicketDto ticketDto = new TicketDto();

        ticketDto.setTicketType(ticket.getTicketType());
        ticketDto.setFine(ticket.getFine());
        ticketDto.setPaid(ticket.getPaid());
        ticketDto.setTicketNumber(ticket.getTicketId());
        ticketDto.setPersonalName(ticket.getFirstName() + " " + ticket.getLastName() );


        return ticketDto;
    }


    public static Ticket mapTicketDtoToTicket(TicketDto ticketDto, BigDecimal fine)
    {
        Ticket ticket = new Ticket();

        String name = ticketDto.getPersonalName();
        String[] names = name.split(" ");
        ticket.setFirstName(names[0]);
        ticket.setLastName(names[1]);
        ticket.setFine(fine);

        ticket.setTicketType(ticketDto.getTicketType());

        return ticket;
    }
}
