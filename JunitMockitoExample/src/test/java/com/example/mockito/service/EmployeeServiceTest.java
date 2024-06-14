package com.example.mockito.service;

import com.example.mockito.model.Employee;
import com.example.mockito.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder().id("1").name("Rahul").age(20).build();
    }

    @Test
    void saveEmployee() {
        //given
        when(employeeRepository.save(employee)).thenReturn(employee);

        //when
        Employee emp = employeeService.saveEmployee(employee);

        //then
        assertNotNull(emp);
        assertThat(emp.getAge()).isEqualTo(20);
        assertThat(emp.getName()).isEqualTo("Rahul");


    }

    @Test
    void getAllEmployees() {
        //given
        Employee emp2 = Employee.builder().name("John").age(25).build();
        when(employeeRepository.findAll()).thenReturn(List.of(employee,emp2));

        //when
        List<Employee> empList = employeeService.getAllEmployees();

        //then
        assertThat(empList.size()).isEqualTo(2);
        assertThat(empList.get(0).getName()).isEqualTo("Rahul");
        assertThat(empList.get(1).getName()).isEqualTo("John");
    }

    @Test
    void getEmployeeById() {
        //given
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));

        //when
        Employee emp = employeeService.getEmployeeById("1");

        //then
        assertNotNull(emp);
        assertThat(emp.getId()).isEqualTo("1");
        assertThat(emp.getName()).isEqualTo("Rahul");
    }

    @Test
    void updateEmployee() {
        //given
        when(employeeRepository.save(employee)).thenReturn(employee);
        employee.setAge(30);
        employee.setName("Ram");

        //when
        Employee emp = employeeService.updateEmployee(employee);

        assertThat(emp).isNotNull();
        assertThat(emp.getName()).isEqualTo("Ram");
    }

    @Test
    void deleteEmployeeById() {
        //given
        doNothing().when(employeeRepository).deleteById("1");

        employeeService.deleteEmployeeById(employee.getId());

        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }

    @Test
    void deleteAllEmployees() {
        doNothing().when(employeeRepository).deleteAll();

        employeeService.deleteAllEmployees();

        verify(employeeRepository, times(1)).deleteAll();
    }


}