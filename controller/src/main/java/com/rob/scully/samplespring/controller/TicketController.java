package com.rob.scully.samplespring.controller;

import com.rob.scully.samplespring.controller.exception.TicketNotFoundException;
import com.rob.scully.samplespring.controller.helper.ControllerHelpers;
import com.rob.scully.samplespring.domain.Ticket;
import com.rob.scully.samplespring.domain.enums.TicketType;
import com.rob.scully.samplespring.dto.TicketDto;
import com.rob.scully.samplespring.service.TicketService;
import com.rob.scully.samplespring.utility.TicketUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/tickets")
@Slf4j
public class TicketController {

    private TicketService ticketService;

    private BigDecimal speedingFine;

    private BigDecimal parkingFine;

    @Autowired
    public TicketController(TicketService ticketService,
                            @Value("${ticket.fine.speeding}") BigDecimal speedingFine,
                            @Value("${ticket.fine.parking}") BigDecimal parkingFine)
    {
        this.ticketService = ticketService;
        this.speedingFine = speedingFine;
        this.parkingFine = parkingFine;
    }

    /**
     * Gets a ticket by ticket Id
     * @param ticketId - the id of the ticket we are searaching for
     * @return - the newly created ticket
     * @throws TicketNotFoundException - return exception if ticket not found
     */
    @GetMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> getTicketByTicketId(@PathVariable String ticketId) throws TicketNotFoundException {

        log.info("Received a request to find ticket by id={}", ticketId);
        Ticket ticket = ticketService.getTicketById(ticketId);

        if(ticket == null) {
            throw new TicketNotFoundException("Could not find ticket with id=" + ticketId);
        }

        TicketDto ticketDto = TicketUtility.mapDomainToTicketDto(ticket);

        return new ResponseEntity<>(ticketDto, HttpStatus.OK);

    }

    /**
     * Creates a new ticket
     *
     * @param ticketDto - the details of the ticket we are to create
     * @param bindingResult - binding result if the binding is correct
     *
     * @return the newly created ticket
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> createTicket(@Validated @RequestBody TicketDto ticketDto, BindingResult bindingResult) {

        ControllerHelpers.checkBinding(bindingResult);

        BigDecimal fine = ticketDto.getTicketType().equals(TicketType.Speeding) ? speedingFine : parkingFine;

        log.info("Ticket Fine={}", fine);
        Ticket ticket = TicketUtility.mapTicketDtoToTicket(ticketDto, fine);

        ticket = ticketService.createNewTicket(ticket);

        TicketDto createdTicket = TicketUtility.mapDomainToTicketDto(ticket);

        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }


    /**
     * Updates a ticket status to paid
     * @param ticketDto - the dto containing the id of the ticket we need to update
     * @return - the updated ticket with status
     * @throws TicketNotFoundException - thrown if the ticket could not be found
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDto> closeTicketAsPaid(@Validated  @RequestBody TicketDto ticketDto, BindingResult bindingResult) throws TicketNotFoundException
    {
        ControllerHelpers.checkBinding(bindingResult);

        String ticketId = ticketDto.getTicketNumber();
        log.info("Received a request to pay ticket with ticketId={}", ticketId);

        Ticket ticket  = ticketService.getTicketById(ticketId.toString());

        if(ticket == null) {
            throw new TicketNotFoundException("Could not find ticket with id=" + ticketId);
        }

        TicketDto responseTicketDto = TicketUtility.mapDomainToTicketDto(ticketService.updateTicketStatusToPaid(ticket));

        return new ResponseEntity<>(responseTicketDto, HttpStatus.OK);
    }
}
