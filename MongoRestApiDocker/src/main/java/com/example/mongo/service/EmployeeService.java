package com.example.mongo.service;

import com.example.mongo.model.Address;
import com.example.mongo.model.Employee;
import com.example.mongo.repository.AddressRepository;
import com.example.mongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Employee saveEmployee(Employee employee) {
        List<Address> addressList = addressRepository.saveAll(employee.getAddress());
        employee.setAddress(addressList);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
