package com.mitarbeiterverwaltung.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * JPA Entity zur Speicherung der Mitarbeiterdaten.
 * Bildet die Tabelle 'employee' in der Datenbank ab.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /** Eindeutige, automatisch generierte ID. Wird als String gespeichert (UUID). */
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private LocalDate entryDate;

    /** Skilllevel als Supportmitarbeiter (muss zwischen 1 und 5 liegen). */
    private int skillLevel;

    /**
     * Konstruktor, der automatisch eine UUID generiert, wenn ein neuer Mitarbeiter erstellt wird.
     * @param firstName Vorname des Mitarbeiters.
     * @param lastName Nachname des Mitarbeiters.
     * @param entryDate Beitrittsdatum zur Firma.
     * @param skillLevel Skilllevel (1-5).
     */
    public Employee(String firstName, String lastName, LocalDate entryDate, int skillLevel) {
        this.id = UUID.randomUUID().toString(); // Automatische eindeutige ID-Generierung
        this.firstName = firstName;
        this.lastName = lastName;
        this.entryDate = entryDate;
        this.skillLevel = skillLevel;
    }
}