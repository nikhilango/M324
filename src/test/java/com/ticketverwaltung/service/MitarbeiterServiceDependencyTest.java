package com.ticketverwaltung.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.ticketverwaltung.dto.EmployeeResponseDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-End Integration Test for Cross-Service Communication.
 * This test ensures that the Ticketverwaltung (Consumer) "understands"
 * the Mitarbeiterverwaltung (Provider) API structure.
 */
@SpringBootTest
class MitarbeiterServiceDependencyTest {

    @Autowired
    private RestTemplate restTemplate;

    private static WireMockServer wireMockServer;

    @Value("${employee.service.url}")
    private String employeeServiceUrl;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(0);
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
    void reset() {
        wireMockServer.resetAll();
    }

    /**
     * Verifies that the Ticketverwaltung can correctly parse a JSON response
     * matching the ACTUAL format of the Mitarbeiterverwaltung.
     */
    @Test
    void shouldCorrectyParseMitarbeiterResponse() {
        // Arrange: Simulate the exact JSON output of Mitarbeiterverwaltung
        String employeeJson = "[{" +
                "\"id\": \"uuid-1\"," +
                "\"firstName\": \"Max\"," +
                "\"lastName\": \"Mustermann\"," +
                "\"entryDate\": \"2021-05-15\"," +
                "\"skillLevel\": 3" +
                "}]";

        wireMockServer.stubFor(get(urlEqualTo("/api/employees"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(employeeJson)
                        .withStatus(200)));

        // Act: Perform the call as the TicketService would
        EmployeeResponseDTO[] response = restTemplate.getForObject(wireMockServer.baseUrl() + "/api/employees",
                EmployeeResponseDTO[].class);

        // Assert: Verify mapping works even if we only care about a subset of fields
        assertNotNull(response);
        assertEquals(1, response.length);
        assertEquals("uuid-1", response[0].getId());
        assertEquals("Max", response[0].getFirstName());
        assertEquals("Mustermann", response[0].getLastName());
    }

    @Test
    void shouldHandleEmptyMitarbeiterResponse() {
        // Arrange
        wireMockServer.stubFor(get(urlEqualTo("/api/employees"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[]")
                        .withStatus(200)));

        // Act
        EmployeeResponseDTO[] response = restTemplate.getForObject(wireMockServer.baseUrl() + "/api/employees",
                EmployeeResponseDTO[].class);

        // Assert
        assertNotNull(response);
        assertEquals(0, response.length);
    }

    @Test
    void shouldHandleProviderError() {
        // Arrange
        wireMockServer.stubFor(get(urlEqualTo("/api/employees"))
                .willReturn(aResponse()
                        .withStatus(500)));

        // Act & Assert
        assertThrows(Exception.class, () -> restTemplate.getForObject(wireMockServer.baseUrl() + "/api/employees",
                EmployeeResponseDTO[].class));
    }
}
