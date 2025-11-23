package com.mitarbeiterverwaltung.service;

import com.mitarbeiterverwaltung.dto.EmployeeCreateDTO;
import com.mitarbeiterverwaltung.model.Employee;
import com.mitarbeiterverwaltung.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests für die EmployeeService-Klasse.
 * Die Tests sind isoliert, indem das EmployeeRepository mit Mockito gemockt wird.
 * Dadurch wird die Geschäftslogik der Service-Schicht unabhängig von der Datenbank getestet.
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    /**
     * Testfall für User Story 1 (Happy Path): Erstellung eines gültigen Mitarbeiters.
     * Stellt sicher, dass die createEmployee-Methode eine neue Employee-Entität mit einer
     * automatisch generierten, eindeutigen ID erstellt und speichert.
     */
    @Test
    void createEmployee_validInput_returnsSavedEmployeeWithId() {
        EmployeeCreateDTO dto = new EmployeeCreateDTO();
        dto.setFirstName("Max");
        dto.setLastName("Muster");
        dto.setEntryDate(LocalDate.of(2021, 5, 15));
        dto.setSkillLevel(4);

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.createEmployee(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Max", result.getFirstName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    /**
     * Testfall für User Story 2 (Happy Path): Auslesen aller Mitarbeiter, wenn welche vorhanden sind.
     * Stellt sicher, dass alle Mitarbeiter vom Repository zurückgegeben werden.
     */
    @Test
    void findAllEmployees_employeesExist_returnsListOfEmployees() {
        Employee emp1 = new Employee("1", "Mia", "Muster", LocalDate.now(), 3);
        Employee emp2 = new Employee("2", "Tom", "Tester", LocalDate.now(), 5);
        List<Employee> mockList = List.of(emp1, emp2);

        when(employeeRepository.findAll()).thenReturn(mockList);

        List<Employee> result = employeeService.findAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    /**
     * Testfall für User Story 2 (Sad Path): Auslesen aller Mitarbeiter, wenn keine vorhanden sind.
     * Stellt sicher, dass eine leere Liste zurückgegeben wird.
     */
    @Test
    void findAllEmployees_noEmployeesExist_returnsEmptyList() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        List<Employee> result = employeeService.findAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findAll();
    }
}