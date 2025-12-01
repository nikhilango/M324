package com.ticketverwaltung.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketverwaltung.dto.TicketCreateDTO;
import com.ticketverwaltung.model.Ticket;
import com.ticketverwaltung.model.TicketStatus;
import com.ticketverwaltung.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Unit Tests for the TicketController.
 * Tests the REST interface for tickets.
 */
@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests the Happy Path: POST /api/tickets with valid data.
     * Expects: 201 Created.
     */
    @Test
    void createTicket_HappyPath() throws Exception {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Controller Test Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-1");

        Ticket ticket = new Ticket();
        ticket.setId("ticket-1");
        ticket.setTitle("Controller Test Ticket");
        ticket.setStatus(TicketStatus.OPEN);

        when(ticketService.createTicket(any(TicketCreateDTO.class))).thenReturn(ticket);

        // Act & Assert
        mockMvc.perform(post("/api/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("ticket-1"))
                .andExpect(jsonPath("$.title").value("Controller Test Ticket"));
    }

    /**
     * Tests the Sad Path: POST /api/tickets with invalid data (Title missing).
     * Expects: 400 Bad Request (due to @Valid).
     */
    @Test
    void createTicket_SadPath_InvalidInput() throws Exception {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        // Title missing
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-1");

        // Act & Assert
        mockMvc.perform(post("/api/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests the Sad Path: Service throws an exception (e.g. validation error).
     * Expects: 400 Bad Request.
     */
    @Test
    void createTicket_SadPath_ServiceError() throws Exception {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Invalid Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-1");

        when(ticketService.createTicket(any(TicketCreateDTO.class)))
                .thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,
                        "Validation Error"));

        // Act & Assert
        mockMvc.perform(post("/api/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
