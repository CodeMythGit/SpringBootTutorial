package com.example.mockito.repository;

import com.example.mockito.config.MongoDbConfiguration;
import com.example.mockito.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@Import(MongoDbConfiguration.MongoConfig.class)
@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest extends MongoDbConfiguration{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveMethod() {
        //given
        Employee employee = Employee.builder().name("Rahul").age(12).build();

        //when
        Employee savedEmployee = employeeRepository.save(employee);

        //then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
    }

    @Test
    public void givenEmployee_whenFindEmployeeById_thenReturnEmployee(){
        //given
        Employee employee = Employee.builder().name("Rahul").age(12).build();
        Employee savedEmployee = employeeRepository.save(employee);

        //when
        Optional<Employee> emp = employeeRepository.findById(savedEmployee.getId());

        //then
        assertTrue(emp.isPresent());
        assertThat(emp.get()).isNotNull();
        assertThat(emp.get().getName()).isEqualTo("Rahul");
    }

    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnEmployee(){
        //given
        Employee employee = Employee.builder().name("Rahul").age(12).build();
        Employee savedEmployee = employeeRepository.save(employee);

        savedEmployee.setName("Rahul 123");
        employeeRepository.save(savedEmployee);

        //when
        Optional<Employee> emp = employeeRepository.findById(savedEmployee.getId());

        //then
        assertTrue(emp.isPresent());
        assertThat(emp.get().getName()).isEqualTo("Rahul 123");
    }

    @Test
    public void givenEmployee_whenDeleteEmployee_thenReturnNothing(){
        //given
        Employee employee = Employee.builder().name("Rahul").age(12).build();
        Employee savedEmployee = employeeRepository.save(employee);

        //when
        employeeRepository.deleteById(savedEmployee.getId());

        //then
        Optional<Employee> emp = employeeRepository.findById(savedEmployee.getId());
        assertTrue(emp.isEmpty());

    }

}