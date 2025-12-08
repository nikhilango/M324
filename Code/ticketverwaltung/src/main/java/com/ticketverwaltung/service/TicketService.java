package com.ticketverwaltung.service;

import com.ticketverwaltung.dto.EmployeeResponseDTO;
import com.ticketverwaltung.dto.TicketCreateDTO;
import com.ticketverwaltung.model.Ticket;
import com.ticketverwaltung.model.TicketStatus;
import com.ticketverwaltung.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RestTemplate restTemplate;

    @Value("${employee.service.url}")
    private String employeeServiceUrl;

    public TicketService(TicketRepository ticketRepository, RestTemplate restTemplate) {
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;
    }

    public Ticket createTicket(TicketCreateDTO createDTO) {
        // 1. Validate Employee
        validateEmployee(createDTO.getEmployeeId());

        // 2. Validate Dates based on Status
        validateDates(createDTO);

        // 3. Create Ticket
        Ticket ticket = new Ticket(
                createDTO.getTitle(),
                createDTO.getDescription(),
                createDTO.getStatus(),
                createDTO.getEmployeeId(),
                createDTO.getReviewDate(),
                createDTO.getDoneDate());

        return ticketRepository.save(ticket);
    }

    private void validateEmployee(String employeeId) {

        try {
            EmployeeResponseDTO[] employees = restTemplate.getForObject(employeeServiceUrl,
                    EmployeeResponseDTO[].class);
            if (employees == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Mitarbeiter-Service nicht erreichbar oder leer.");
            }

            boolean exists = Arrays.stream(employees).anyMatch(e -> e.getId().equals(employeeId));
            if (!exists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Mitarbeiter mit ID " + employeeId + " existiert nicht.");
            }

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Fehler beim Abruf der Mitarbeiter: " + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Interner Fehler bei der Mitarbeiter-Validierung: " + e.getMessage());
        }
    }

    private void validateDates(TicketCreateDTO dto) {
        TicketStatus status = dto.getStatus();

        if (status == TicketStatus.OPEN || status == TicketStatus.IN_PROGRESS) {
            if (dto.getReviewDate() != null || dto.getDoneDate() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Review- und Done-Datum dürfen nicht gesetzt sein, wenn Status OPEN oder IN_PROGRESS ist.");
            }
        } else if (status == TicketStatus.REVIEW) {
            if (dto.getReviewDate() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Review-Datum muss gesetzt sein, wenn Status REVIEW ist.");
            }
            if (dto.getDoneDate() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Done-Datum darf nicht gesetzt sein, wenn Status REVIEW ist.");
            }
        } else if (status == TicketStatus.DONE) {
            if (dto.getReviewDate() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Review-Datum muss gesetzt sein, wenn Status DONE ist (da es den Review-Status durchlaufen haben muss).");
            }
            if (dto.getDoneDate() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Done-Datum muss gesetzt sein, wenn Status DONE ist.");
            }
        }
    }
}
