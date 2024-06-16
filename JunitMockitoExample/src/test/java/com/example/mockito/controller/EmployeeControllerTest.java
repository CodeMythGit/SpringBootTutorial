package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    public void setUp() {
        employee = Employee.builder().id("199").name("John").age(20).build();
    }

    @Test
    void createEmployee() throws Exception {
        //given
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        //when+then
        ResultActions result = mockMvc.perform(post("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        //then
                /*
                {
                    "id:"1",
                    "name":"kumar",
                    "age":20
                }
                 */
        result.andExpect(jsonPath("$.id").value(199))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value("20"));

    }

    @Test
    void createEmployee_failedRequest() throws Exception {
        //given
        Employee emp = Employee.builder().id("199").name("John").age(80).build();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(emp);

        //when+then
        ResultActions result = mockMvc.perform(post("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)));
        //then
        result.andExpect(jsonPath("$.age").value("Age must be between 18 and 60"));
    }

    @Test
    void getAllEmployees() throws Exception {
        //given
        Employee emp2 = Employee.builder().id("2").name("Kumar").age(25).build();
        when(employeeService.getAllEmployees()).thenReturn(List.of(employee,emp2));

        //when + then
        mockMvc.perform(get("/emp")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(199))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById("199")).thenReturn(employee);

        //when+then
        mockMvc.perform(get("/emp/1")
//                .param("id","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Employee not found for id 1"));
//                .andExpect(jsonPath("$.id").value(199))
//                .andExpect(jsonPath("$.name").value("John"));

    }

    @Test
    void getEmployeeById_Positive() throws Exception {
        when(employeeService.getEmployeeById("199")).thenReturn(employee);

        //when+then
        mockMvc.perform(get("/emp/199")
//                .param("id","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(199))
                .andExpect(jsonPath("$.name").value("John"));

    }

    @Test
    void testUpdateEmployeeById() throws Exception {
        //given
        when(employeeService.getEmployeeById("199")).thenReturn(employee);
        Employee updatedEmployee = Employee.builder().id("199").name("John Updated").age(25).build();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);

        //when+then
        mockMvc.perform(put("/emp/199")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(199))
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.age").value(25));


    }

    @Test
    void testNegativeScenarioUpdateEmployeeById() throws Exception {
        //given
        when(employeeService.getEmployeeById("199")).thenReturn(employee);
        Employee updatedEmployee = Employee.builder().id("199").name("John Updated").age(25).build();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);

        //when+then
        mockMvc.perform(put("/emp/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee not found"));


    }

    @Test
    void deleteEmployeeById() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById("199");

        mockMvc.perform(delete("/emp/199")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted successfully"));
    }

    @Test
    void deleteAllEmployees() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById("199");

        mockMvc.perform(delete("/emp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("All Employee deleted successfully"));
    }
}