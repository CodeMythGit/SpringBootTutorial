package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().id("1").name("Rahul").age(12).build();
    }

    @Test
    void createEmployee() throws Exception {
        //given
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/emp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Rahul"))
                .andExpect(jsonPath("$.age").value("12"));
    }

    @Test
    void getAllEmployees() throws Exception {
        Employee emp2 = Employee.builder().id("2").name("Ajay").age(50).build();
        when(employeeService.getAllEmployees()).thenReturn(List.of(employee, emp2));

        mockMvc.perform(get("/emp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Rahul"));

    }

    @Test
    void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(anyString())).thenReturn(employee);

        mockMvc.perform(get("/emp/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    void updateEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(employee.getId())).thenReturn(employee);
        Employee updatedEmployee = Employee.builder().id("1").age(25).name("Rahul Updated").build();
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/emp/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Rahul Updated"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    void deleteEmployeeById() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(employee.getId());

        mockMvc.perform(delete("/emp/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Employee deleted successfully"));
    }

    @Test
    void deleteAllEmployees() throws Exception {
        doNothing().when(employeeService).deleteAllEmployees();

        mockMvc.perform(delete("/emp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("All Employee deleted successfully"));
    }
}