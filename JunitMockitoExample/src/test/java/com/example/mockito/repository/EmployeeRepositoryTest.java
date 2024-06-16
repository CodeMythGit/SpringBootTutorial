package com.example.mockito.repository;

import com.example.mockito.config.MongoConfiguration;
import com.example.mockito.model.Employee;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
@Import(MongoConfiguration.MongoConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest extends MongoConfiguration {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    public void cleanUp() {
        employeeRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveMethod() {
        //given
        Employee emp = Employee.builder().name("Kumar").age(25).build();

        //when
        Employee employee = employeeRepository.save(emp);

        //then
        assertNotNull(employee);
        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getName()).isEqualTo("Kumar");
        assertThat(employee.getAge()).isEqualTo(25);
    }

    @Test
    @Order(2)
    public void givenEmployee_whenFindById_thenReturnEmployee() {
        //given
        Employee emp = Employee.builder().name("Kumar").age(70).build();
        Employee savedEmployee = employeeRepository.save(emp);

        //when
        Optional<Employee> employee = employeeRepository.findById(savedEmployee.getId());

        //then
        assertTrue(employee.isPresent());
        assertThat(employee.get()).isNotNull();
        assertThat(employee.get().getName()).isEqualTo("Kumar");
        assertThat(employee.get().getAge()).isEqualTo(70);
    }

    @Test
    public void testEmployeeUpdateMethod() {
        //given
        Employee emp = Employee.builder().name("Kumar").age(25).build();
        Employee savedEmployee = employeeRepository.save(emp);

        savedEmployee.setName("Kumar Updated");
        employeeRepository.save(savedEmployee);

        //when
        Optional<Employee> employee = employeeRepository.findById(savedEmployee.getId());

        //then
        assertTrue(employee.isPresent());
        assertThat(employee.get().getName()).isEqualTo("Kumar Updated");
    }

    @Test
    public void testDeleteByIdForEmployee() {
        //given
        Employee emp = Employee.builder().name("Kumar").age(25).build();
        Employee savedEmployee = employeeRepository.save(emp);

        //when
        employeeRepository.deleteById(savedEmployee.getId());

        //then
        Optional<Employee> employee = employeeRepository.findById(savedEmployee.getId());
        assertFalse(employee.isPresent());
    }

    @Test
    public void testCheckDeleteOnEmployeeListByDeletingOneRecord() {
        //before
        List<Employee> saveEmpList = employeeRepository.findAll();
        assertThat(saveEmpList.size()).isEqualTo(0);

        //given
        Employee emp1 = Employee.builder().name("Kumar").age(25).build();
        Employee emp2 = Employee.builder().name("Ajay").age(40).build();
        Employee emp3 = Employee.builder().name("Ram").age(30).build();

        List<Employee> employeeList = List.of(emp1, emp2, emp3);

        saveEmpList = employeeRepository.saveAll(employeeList);

        //when
        employeeRepository.deleteById(saveEmpList.get(1).getId());

        //then
        saveEmpList = employeeRepository.findAll();
        assertThat(saveEmpList.size()).isEqualTo(2);
    }

}