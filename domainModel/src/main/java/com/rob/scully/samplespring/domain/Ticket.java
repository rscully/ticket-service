package com.rob.scully.samplespring.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rob.scully.samplespring.domain.enums.TicketType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * A base ticket class
 *
 * @author rscully 27/08/2020
 */
@Data
@Document(collection = "tickets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @Id
    private String ticketId;

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    @Indexed
    private BigDecimal fine;

    @Indexed
    private TicketType ticketType;

    @Indexed
    private Boolean paid;

    @Version
    private Long version;

}
