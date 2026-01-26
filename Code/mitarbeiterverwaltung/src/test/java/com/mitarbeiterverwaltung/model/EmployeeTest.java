package com.mitarbeiterverwaltung.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void employeeConstructor_setsAllFieldsAndGeneratesId() {
        // Arrange & Act
        LocalDate entryDate = LocalDate.of(2023, 1, 1);
        Employee employee = new Employee("Jane", "Doe", entryDate, 5);

        // Assert
        assertNotNull(employee.getId());
        assertFalse(employee.getId().isEmpty());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(entryDate, employee.getEntryDate());
        assertEquals(5, employee.getSkillLevel());
    }

    @Test
    void employeeSettersAndGetters_workCorrectly() {
        // Arrange
        Employee employee = new Employee();

        // Act
        employee.setId("fixed-id");
        employee.setFirstName("John");
        employee.setLastName("Smith");

        // Assert
        assertEquals("fixed-id", employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());
    }
}
