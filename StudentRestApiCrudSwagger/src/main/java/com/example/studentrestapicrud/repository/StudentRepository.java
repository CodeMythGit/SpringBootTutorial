package com.example.studentrestapicrud.repository;

import com.example.studentrestapicrud.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Transactional
    Optional<Student> findByCity(String city);

    @Transactional
    Optional<List<Student>> findByAgeLessThan(Integer age);
}
