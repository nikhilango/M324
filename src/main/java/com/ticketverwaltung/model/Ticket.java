package com.ticketverwaltung.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String title;
    private String description;
    private Instant reviewDate;
    private Instant doneDate;
    private String employeeId;
    private Instant createdAt;

    public Ticket(String title, String description, TicketStatus status, String employeeId, Instant reviewDate,
            Instant doneDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
        this.reviewDate = reviewDate;
        this.doneDate = doneDate;
        this.createdAt = Instant.now();
    }
}
