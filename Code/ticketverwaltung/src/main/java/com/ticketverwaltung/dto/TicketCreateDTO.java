package com.ticketverwaltung.dto;

import com.ticketverwaltung.model.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class TicketCreateDTO {

    @NotBlank(message = "Titel darf nicht leer sein.")
    private String title;

    private String description;

    @NotNull(message = "Status ist erforderlich.")
    private TicketStatus status;

    @NotBlank(message = "Mitarbeiter-id ist erforderlich.")
    private String employeeId;

    private Instant reviewDate;
    private Instant doneDate;
}
