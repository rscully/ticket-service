package com.rob.scully.samplespring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.scully.samplespring.controller.advice.GlobalExceptionHandlingControllerAdvice;
import com.rob.scully.samplespring.controller.helper.AbstractControllerTest;
import com.rob.scully.samplespring.domain.Ticket;
import com.rob.scully.samplespring.domain.enums.TicketType;
import com.rob.scully.samplespring.dto.TicketDto;
import com.rob.scully.samplespring.service.TicketService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TicketService.class)
public class TicketControllerTest extends AbstractControllerTest {

    private static final Logger log = LoggerFactory.getLogger(TicketControllerTest.class);

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private ObjectMapper objectMapper = new ObjectMapper();

    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        initMocks(this);
        ticketController = new TicketController(ticketService, new BigDecimal(30), new BigDecimal(40)
        );
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController)
                .setControllerAdvice(new GlobalExceptionHandlingControllerAdvice())
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void createNewTicket() throws Exception {
        generateRestDocumentationResultHandler("{class-name}/{method-name}");

        //arrange

        UUID uuid = UUID.randomUUID();
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketType(TicketType.Speeding);
        ticketDto.setPersonalName("Rob Scully");

        Ticket ticket = new Ticket();
        ticket.setTicketId(uuid.toString());
        ticket.setFirstName("Robert");
        ticket.setLastName("Scully");
        ticket.setPaid(false);
        ticket.setFine(new BigDecimal(80));
        ticket.setTicketType(ticketDto.getTicketType());

        when(ticketService.createNewTicket(any(Ticket.class))).thenReturn(ticket);

        String json = objectMapper.writeValueAsString(ticketDto);

        FieldDescriptor[] documentFields = new FieldDescriptor[]{
                fieldWithPath("ticketNumber").description("The number of the ticket just created"),
                fieldWithPath("fine").description("The cost of the ticke4t"),
                fieldWithPath("paid").description("True if the ticket is paid, false otherwise"),
        };

        mockMvc.perform(post("/tickets").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andDo(restDocumentationResultHandler)
                .andDo(restDocumentationResultHandler.document(relaxedResponseFields(
                        documentFields)
                ));
    }

    @Test
    public void getTicketById() throws Exception {
        generateRestDocumentationResultHandler("{class-name}/{method-name}");

        UUID uuid = UUID.randomUUID();
        Ticket ticket = new Ticket();
        ticket.setTicketId(uuid.toString());
        ticket.setFine(new BigDecimal(80));
        ticket.setTicketType(TicketType.Speeding);
        ticket.setPaid(false);
        ticket.setLastName("Scully");
        ticket.setFirstName("Rob");


        when(ticketService.getTicketById(anyString())).thenReturn(ticket);

        FieldDescriptor[] documentFields = new FieldDescriptor[]{
                fieldWithPath("ticketNumber").description("The unique ticket number of this ticket"),
                fieldWithPath("ticketType").description("The type of this ticket e.g. speeding, parking etc"),
                fieldWithPath("fine").description("The amount of the fine"),
                fieldWithPath("paid").description("True of the fine was paid, false otherwise"),
                fieldWithPath("personalName").description("The name of the person to whom this ticket was issued")
        };


        mockMvc.perform(get("/tickets/" + uuid.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler)
                .andDo(restDocumentationResultHandler.document(relaxedResponseFields(
                        documentFields))
                );
    }

    @Test
    public void closeTicket() throws Exception {
        generateRestDocumentationResultHandler("{class-name}/{method-name}");

        UUID uuid = UUID.randomUUID();
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketNumber(uuid.toString());

        Ticket ticket = new Ticket();
        ticket.setTicketId(uuid.toString());
        ticket.setFine(new BigDecimal(80));
        ticket.setTicketType(TicketType.Speeding);
        ticket.setPaid(true);
        ticket.setLastName("Scully");
        ticket.setFirstName("Rob");

        String json = objectMapper.writeValueAsString(ticketDto);

        when(ticketService.updateTicketStatusToPaid(any(Ticket.class))).thenReturn(ticket);
        when(ticketService.getTicketById(anyString())).thenReturn(ticket);


        FieldDescriptor[] documentFields = new FieldDescriptor[]{
                fieldWithPath("ticketNumber").description("The unique ticket number of this ticket"),
                fieldWithPath("ticketType").description("The type of this ticket e.g. speeding, parking etc"),
                fieldWithPath("fine").description("The amount of the fine"),
                fieldWithPath("paid").description("True of the fine was paid, false otherwise"),
                fieldWithPath("personalName").description("The name of the person to whom this ticket was issued")

        };


        mockMvc.perform(put("/tickets").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler)
                .andDo(restDocumentationResultHandler.document(relaxedResponseFields(
                        documentFields)
                ));
    }



}
