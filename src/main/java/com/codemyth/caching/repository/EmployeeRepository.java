package com.codemyth.caching.repository;

import com.codemyth.caching.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);

    void deleteByName(String name);

    void deleteAllByName(String name);
}
