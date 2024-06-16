package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<Object> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String id) {
        Employee emp = employeeService.getEmployeeById(id);
        if(emp!=null) {
            return ResponseEntity.ok(emp);
        }else{
            return ResponseEntity.ok("Employee not found for id " + id);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployeeById(@PathVariable String id, @RequestBody Employee employee) {
        Employee oldEmp = employeeService.getEmployeeById(id);
        if (oldEmp != null) {
            BeanUtils.copyProperties(employee, oldEmp, "id");
            return ResponseEntity.ok(employeeService.saveEmployee(oldEmp));
        } else {
            return ResponseEntity.ok("Employee not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable String id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllEmployees(){
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok("All Employee deleted successfully");
    }
}
