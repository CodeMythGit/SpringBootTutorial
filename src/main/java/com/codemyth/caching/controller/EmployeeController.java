package com.codemyth.caching.controller;

import com.codemyth.caching.model.Employee;
import com.codemyth.caching.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //create
    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created successfully...");
    }

    @PostMapping("/saveWithParam")
    public ResponseEntity<String> saveEmployeeWithParam(@RequestParam("name") String name, @RequestParam("salary") Double salary) {
        employeeService.saveEmployeeWithParam(name, salary);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created successfully with param...");
    }

    //read
    @GetMapping("/name/{name}")
    public ResponseEntity<Employee> getByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.findByName(name));
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Updated Successfully...");
    }

    //delete
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name){
        employeeService.deleteAllByName(name);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted Successfully with name "+ name);
    }

    //deleteAll
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All Employee deleted Successfully");
    }
}
