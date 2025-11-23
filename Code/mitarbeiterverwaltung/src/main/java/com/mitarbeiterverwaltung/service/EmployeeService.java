package com.mitarbeiterverwaltung.service;

import com.mitarbeiterverwaltung.dto.EmployeeCreateDTO;
import com.mitarbeiterverwaltung.model.Employee;
import com.mitarbeiterverwaltung.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-Schicht für die Geschäftslogik der Mitarbeiterverwaltung.
 * Verwaltet die Erstellung (User Story 1) und das Auslesen (User Story 2) von Mitarbeitern.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Konstruktor-Injection des EmployeeRepository.
     * @param employeeRepository Repository für den Datenbankzugriff.
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * User Story 1: Erstellt einen neuen Mitarbeiter basierend auf den DTO-Daten.
     *
     * @param createDTO Daten des neuen Mitarbeiters.
     * @return Der gespeicherte Employee mit der automatisch generierten ID.
     */
    public Employee createEmployee(EmployeeCreateDTO createDTO) {
        Employee newEmployee = new Employee(
            createDTO.getFirstName(),
            createDTO.getLastName(),
            createDTO.getEntryDate(),
            createDTO.getSkillLevel()
        );

        return employeeRepository.save(newEmployee);
    }

    /**
     * User Story 2: Liest alle erfassten Mitarbeiter aus.
     *
     * @return Eine Liste aller Mitarbeiter. Kann leer sein.
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Hilfsmethode: Findet einen Mitarbeiter anhand seiner ID.
     *
     * @param id Eindeutige ID des Mitarbeiters.
     * @return Ein Optional, das den Mitarbeiter enthält oder leer ist.
     */
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }
}