package com.codemyth.caching.service;

import com.codemyth.caching.model.Employee;
import com.codemyth.caching.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"emp_cache"})
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @CachePut(key = "#employee.name", value = "emp_cache")
    public Employee saveEmployee(Employee employee) {
        log.info("Saving employee");
        return employeeRepository.save(employee);
    }

    @CachePut(key = "#root.args[0]", value = "emp_cache")
    public Employee saveEmployeeWithParam(String name, Double salary) {
        log.info("Saving employee with param");
        return employeeRepository.save(Employee.builder().name(name).salary(salary).build());
    }

    @Cacheable(key = "#root.args[0]", value = "emp_cache")
    public Employee findByName(String name) {
        log.info("find employee by name "+name);
        return employeeRepository.findByName(name).orElse(Employee.builder().build());
    }

    @CachePut(key = "#employee.name", value = "emp_cache")
    public Employee updateEmployee(Employee employee) {
        log.info("update employee by name "+employee.getName());
        Optional<Employee> empOpt = employeeRepository.findByName(employee.getName());
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            emp.setSalary(employee.getSalary());
            employeeRepository.save(employee);
        }
        return null;
    }

    @Transactional
    @CacheEvict(key = "#root.args[0]", value = "emp_cache")
    public void deleteAllByName(String name) {
        log.info("delete employee by name "+name);
        employeeRepository.deleteAllByName(name);
    }

    @CacheEvict(key = "#root.args[0]", value = "emp_cache", allEntries = true)
    public void deleteAll() {
        log.info("delete all employee");
        employeeRepository.deleteAll();
    }
}
