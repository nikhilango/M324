package com.ticketverwaltung.repository;

import com.ticketverwaltung.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import com.ticketverwaltung.model.TicketStatus;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    long countByStatus(TicketStatus status);
    long countByCreatedAtAfter(Instant date);
}
