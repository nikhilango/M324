package com.mitarbeiterverwaltung.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitarbeiterverwaltung.dto.EmployeeCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Integrationstests für die EmployeeController-Klasse.
 * Manuelle Konfiguration von MockMvc ohne @AutoConfigureMockMvc.
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * Testfall für User Story 1 (Happy Path): Erstellung eines gültigen Mitarbeiters.
     */
    @Test
    void createEmployee_validInput_returns201AndCreatedEmployee() throws Exception {
        EmployeeCreateDTO validDto = new EmployeeCreateDTO();
        validDto.setFirstName("Max");
        validDto.setLastName("Mustermann");
        validDto.setEntryDate(LocalDate.of(2021, 5, 15));
        validDto.setSkillLevel(4);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName", is("Max")))
                .andExpect(jsonPath("$.skillLevel", is(4)));
    }

    /**
     * Testfall für User Story 1 (Sad Path): Ungültiger Skilllevel (außerhalb 1-5).
     */
    @Test
    void createEmployee_invalidSkillLevel_returns400BadRequest() throws Exception {
        EmployeeCreateDTO invalidDto = new EmployeeCreateDTO();
        invalidDto.setFirstName("Max");
        invalidDto.setLastName("Mustermann");
        invalidDto.setEntryDate(LocalDate.now());
        invalidDto.setSkillLevel(6); // Ungültiger Skilllevel

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Skilllevel darf höchstens 5 sein.")));
    }

    /**
     * Testfall für User Story 2 (Happy Path): Auslesen aller Mitarbeiter, nachdem zwei erstellt wurden.
     */
    @Test
    void getAllEmployees_multipleEmployeesExist_returns200AndListOfEmployees() throws Exception {
        EmployeeCreateDTO dto1 = new EmployeeCreateDTO();
        dto1.setFirstName("Alice"); dto1.setLastName("A"); dto1.setEntryDate(LocalDate.now()); dto1.setSkillLevel(1);
        EmployeeCreateDTO dto2 = new EmployeeCreateDTO();
        dto2.setFirstName("Bob"); dto2.setLastName("B"); dto2.setEntryDate(LocalDate.now()); dto2.setSkillLevel(5);

        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto1)));
        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto2)));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Alice")));
    }

    /**
     * Testfall für User Story 2 (Edge Case): Auslesen, wenn keine Mitarbeiter existieren.
     */
    @Test
    void getAllEmployees_noEmployeesExist_returns200AndEmptyList() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}