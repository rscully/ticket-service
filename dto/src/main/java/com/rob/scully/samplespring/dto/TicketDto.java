package com.rob.scully.samplespring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rob.scully.samplespring.domain.enums.TicketType;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {
    private TicketType ticketType;
    private String ticketNumber;
    private String personalName;
    private BigDecimal fine;
    private Boolean paid;
}
