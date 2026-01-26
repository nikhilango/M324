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
        Ticket createdTicket = ticketService.createTicket(createDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getTicketStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total_tickets", ticketRepository.count());
        stats.put("open_tickets", ticketRepository.countByStatus(TicketStatus.OPEN));
        stats.put("in_progress_tickets", ticketRepository.countByStatus(TicketStatus.IN_PROGRESS));
        stats.put("review_tickets", ticketRepository.countByStatus(TicketStatus.REVIEW));
        stats.put("done_tickets", ticketRepository.countByStatus(TicketStatus.DONE));

        Instant last24Hours = Instant.now().minusSeconds(24 * 60 * 60);
        Instant last7Days = Instant.now().minusSeconds(7 * 24 * 60 * 60);

        long newTicketsLast24h = ticketRepository.countByCreatedAtAfter(last24Hours);
        long newTicketsLast7d = ticketRepository.countByCreatedAtAfter(last7Days);

        stats.put("velocity_new_tickets_24h", newTicketsLast24h);
        stats.put("velocity_new_tickets_7d", newTicketsLast7d);
    
        return ResponseEntity.ok(stats);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Fehlerhafte Eingabe. Bitte prüfen Sie die Daten. Detail: " + ex.getMessage());
    }
}
