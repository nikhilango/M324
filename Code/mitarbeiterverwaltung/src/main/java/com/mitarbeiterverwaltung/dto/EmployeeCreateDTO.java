package com.mitarbeiterverwaltung.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) für die Erstellung eines neuen Mitarbeiters (User Story 1).
 * Enthält Validierungsregeln für die eingegebenen Daten.
 */
@Data
public class EmployeeCreateDTO {

    @NotBlank(message = "Vorname darf nicht leer sein.")
    private String firstName;

    @NotBlank(message = "Nachname darf nicht leer sein.")
    private String lastName;

    @NotNull(message = "Beitrittsdatum ist erforderlich.")
    private LocalDate entryDate;

    /**
     * Validierung: Skilllevel muss eine Zahl zwischen 1 und 5 sein.
     */
    @Min(value = 1, message = "Skilllevel muss mindestens 1 sein.")
    @Max(value = 5, message = "Skilllevel darf höchstens 5 sein.")
    private int skillLevel;
}