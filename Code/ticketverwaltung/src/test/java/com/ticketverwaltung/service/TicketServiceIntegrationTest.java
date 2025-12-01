package com.ticketverwaltung.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.ticketverwaltung.dto.TicketCreateDTO;
import com.ticketverwaltung.model.Ticket;
import com.ticketverwaltung.model.TicketStatus;
import com.ticketverwaltung.repository.TicketRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.server.ResponseStatusException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Test using WireMock to verify the connection logic.
 * This test simulates the external Employee Service using WireMock.
 * It verifies that the TicketService actually makes the correct HTTP call.
 */
@SpringBootTest
class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(0); // Random port
        wireMockServer.start();
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("employee.service.url", () -> wireMockServer.baseUrl() + "/api/employees");
    }

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll();
        wireMockServer.resetAll();
    }

    /**
     * Tests that the service makes a GET request to the configured URL
     * and correctly processes a successful response.
     */
    @Test
    void createTicket_Integration_Success() {
        // Arrange
        String employeeId = "emp-integration-1";

        // Stub the external service response
        wireMockServer.stubFor(get(urlEqualTo("/api/employees"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":\"" + employeeId + "\", \"firstName\":\"John\", \"lastName\":\"Doe\"}]")
                        .withStatus(200)));

        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Integration Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId(employeeId);

        // Act
        Ticket result = ticketService.createTicket(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Integration Ticket", result.getTitle());

        // Verify that WireMock received the request
        wireMockServer.verify(getRequestedFor(urlEqualTo("/api/employees")));
    }

    /**
     * Tests that the service handles a 404 from the external service correctly.
     */
    @Test
    void createTicket_Integration_ExternalServiceError() {
        // Arrange
        String employeeId = "emp-integration-1";

        // Stub a 404 response
        wireMockServer.stubFor(get(urlEqualTo("/api/employees"))
                .willReturn(aResponse()
                        .withStatus(404)));

        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Integration Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId(employeeId);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));

        // Verify request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/api/employees")));
    }
}
