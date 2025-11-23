package com.mitarbeiterverwaltung.controller;

import com.mitarbeiterverwaltung.dto.EmployeeCreateDTO;
import com.mitarbeiterverwaltung.model.Employee;
import com.mitarbeiterverwaltung.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für die REST-API der Mitarbeiterverwaltung.
 * Definiert Endpunkte für das Erstellen und Auslesen von Mitarbeitern.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Konstruktor-Injection des EmployeeService.
     * @param employeeService Service-Schicht zur Verarbeitung der Geschäftslogik.
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * User Story 1: Endpunkt zum Erfassen eines neuen Mitarbeiters.
     * URL: POST /api/employees
     * @param createDTO Das DTO mit den zu erfassenden Mitarbeiterdaten.
     * @return Eine HTTP 201 (Created) Response mit dem erstellten Mitarbeiterobjekt.
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeCreateDTO createDTO) {
        Employee createdEmployee = employeeService.createEmployee(createDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    /**
     * User Story 2: Endpunkt zum Auslesen aller erfassten Mitarbeiter.
     * URL: GET /api/employees
     * @return Eine HTTP 200 (OK) Response mit einer Liste aller Mitarbeiter.
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        return ResponseEntity.ok(employees);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Fehlerhafte Eingabe. Bitte prüfen Sie die Daten. Detail: " + ex.getMessage());
    }
}