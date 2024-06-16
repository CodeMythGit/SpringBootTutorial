package com.example.mockito.service;

import com.example.mockito.model.Employee;
import com.example.mockito.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//alternative to mockito

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee emp1;


    @BeforeEach
    void setUp() {
        emp1 = Employee.builder()
                .id("1")
                .name("Kumar")
                .age(22)
                .build();
    }

    @Test
    @DisplayName("Test Save Employee method")
    @Order(1)
    void givenEmployee_whenCallSaveEmployee_thenReturnEmployee() {
        //given
        when(employeeRepository.save(emp1)).thenReturn(emp1);

        //when
        Employee employee = employeeService.saveEmployee(emp1);

        //then
        assertNotNull(employee);
        assertThat(employee.getName()).isEqualTo("Kumar");
        assertThat(employee.getAge()).isEqualTo(22);
    }

    @Test
    @Order(2)
    @DisplayName("Test Get All Employee method")
    void getAllEmployees() {
        //given
        Employee emp2 =  Employee.builder()
                                .id("2")
                                .name("Rahul")
                                .age(25)
                                .build();
        when(employeeRepository.findAll()).thenReturn(List.of(emp1,emp2));

        //when
        List<Employee> employeeList = employeeService.getAllEmployees();

        //then
        assertThat(employeeList.size()).isEqualTo(2);
        assertThat(employeeList.get(0).getName()).isEqualTo("Kumar");
        assertThat(employeeList.get(1).getName()).isEqualTo("Rahul");
    }

    @Test
    @Order(3)
    void getEmployeeById() {
        //given
        when(employeeRepository.findById(any())).thenReturn(Optional.of(emp1));

        //when
        Employee employee = employeeService.getEmployeeById("1");

        //then
        assertNotNull(employee);
        assertThat(employee.getId()).isEqualTo("1");
    }

    @Test
    @Order(4)
    void deleteEmployeeById() {
        //given
        doNothing().when(employeeRepository).deleteById("1");

        //when
        employeeService.deleteEmployeeById("1");

        //then
        verify(employeeRepository, times(1)).deleteById("1");
    }

    @Test
    @Order(5)
    void deleteAllEmployees() {
        //given
        doNothing().when(employeeRepository).deleteAll();

        //when
        employeeService.deleteAllEmployees();

        //then
        verify(employeeRepository, times(1)).deleteAll();
    }
}