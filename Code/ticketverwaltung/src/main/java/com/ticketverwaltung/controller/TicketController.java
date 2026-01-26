package com.ticketverwaltung.controller;

import com.ticketverwaltung.dto.TicketCreateDTO;
import com.ticketverwaltung.model.Ticket;
import com.ticketverwaltung.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketCreateDTO createDTO) {
        // Fix #54: Falls die Validierung fehlschlägt oder der Service eine Exception wirft,
        // wird dies durch den GlobalExceptionHandler abgefangen und als Error-Response zurückgegeben.
        Ticket createdTicket = ticketService.createTicket(createDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Fehlerhafte Eingabe. Bitte prüfen Sie die Daten. Detail: " + ex.getMessage());
    }
}
