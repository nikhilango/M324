package com.ticketverwaltung.service;

import com.ticketverwaltung.dto.EmployeeResponseDTO;
import com.ticketverwaltung.dto.TicketCreateDTO;
import com.ticketverwaltung.model.Ticket;
import com.ticketverwaltung.model.TicketStatus;
import com.ticketverwaltung.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit Tests for the TicketService class.
 * Tests the business logic for ticket creation (User Story 3).
 */
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set the URL for the mock
        ReflectionTestUtils.setField(ticketService, "employeeServiceUrl", "http://localhost:8080/api/employees");
    }

    /**
     * Tests the Happy Path: A ticket with status OPEN is successfully created.
     * Prerequisite: Employee exists.
     */
    @Test
    void createTicket_HappyPath_Open() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-123");

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Ticket result = ticketService.createTicket(dto);

        // Assert
        assertNotNull(result);
        assertEquals("Test Ticket", result.getTitle());
        assertEquals(TicketStatus.OPEN, result.getStatus());
        assertEquals("emp-123", result.getEmployeeId());
    }

    /**
     * Tests the Happy Path: A ticket with status IN_PROGRESS is successfully
     * created.
     * Prerequisite: Employee exists.
     */
    @Test
    void createTicket_HappyPath_InProgress() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Working on it");
        dto.setStatus(TicketStatus.IN_PROGRESS);
        dto.setEmployeeId("emp-123");

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Ticket result = ticketService.createTicket(dto);

        // Assert
        assertNotNull(result);
        assertEquals(TicketStatus.IN_PROGRESS, result.getStatus());
    }

    /**
     * Tests the Happy Path: A ticket with status REVIEW is successfully created.
     * Prerequisite: Review date is set, Done date is null.
     */
    @Test
    void createTicket_HappyPath_Review() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Review Me");
        dto.setStatus(TicketStatus.REVIEW);
        dto.setEmployeeId("emp-123");
        dto.setReviewDate(Instant.now());

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Ticket result = ticketService.createTicket(dto);

        // Assert
        assertNotNull(result);
        assertEquals(TicketStatus.REVIEW, result.getStatus());
        assertNotNull(result.getReviewDate());
        assertNull(result.getDoneDate());
    }

    /**
     * Tests the Happy Path: A ticket with status DONE is successfully created.
     * Prerequisite: Review date and Done date are set.
     */
    @Test
    void createTicket_HappyPath_Done() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Finished");
        dto.setStatus(TicketStatus.DONE);
        dto.setEmployeeId("emp-123");
        dto.setReviewDate(Instant.now().minusSeconds(3600));
        dto.setDoneDate(Instant.now());

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Ticket result = ticketService.createTicket(dto);

        // Assert
        assertNotNull(result);
        assertEquals(TicketStatus.DONE, result.getStatus());
        assertNotNull(result.getReviewDate());
        assertNotNull(result.getDoneDate());
    }

    /**
     * Tests the Sad Path: Employee does not exist.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_EmployeeNotFound() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("unknown-id");

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: External employee service fails (e.g., 404 or 500).
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_ExternalServiceFailure() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-123");

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class)))
                .thenThrow(new HttpClientErrorException(org.springframework.http.HttpStatus.NOT_FOUND));

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: Status OPEN but dates are provided.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_OpenWithDates() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.OPEN);
        dto.setEmployeeId("emp-123");
        dto.setReviewDate(Instant.now()); // Should not be there

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: Status REVIEW but ReviewDate is missing.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_ReviewMissingDate() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.REVIEW);
        dto.setEmployeeId("emp-123");
        // ReviewDate missing

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: Status REVIEW but DoneDate is present.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_ReviewWithDoneDate() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.REVIEW);
        dto.setEmployeeId("emp-123");
        dto.setReviewDate(Instant.now());
        dto.setDoneDate(Instant.now()); // Should not be there

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: Status DONE without DoneDate.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_DoneMissingDate() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.DONE);
        dto.setEmployeeId("emp-123");
        dto.setReviewDate(Instant.now());
        // DoneDate missing

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }

    /**
     * Tests the Sad Path: Status DONE without ReviewDate.
     * Expects: ResponseStatusException.
     */
    @Test
    void createTicket_SadPath_DoneMissingReviewDate() {
        // Arrange
        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("Test Ticket");
        dto.setStatus(TicketStatus.DONE);
        dto.setEmployeeId("emp-123");
        dto.setDoneDate(Instant.now());
        // ReviewDate missing

        EmployeeResponseDTO emp = new EmployeeResponseDTO();
        emp.setId("emp-123");
        EmployeeResponseDTO[] employees = new EmployeeResponseDTO[] { emp };

        when(restTemplate.getForObject(anyString(), eq(EmployeeResponseDTO[].class))).thenReturn(employees);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ticketService.createTicket(dto));
    }
}
