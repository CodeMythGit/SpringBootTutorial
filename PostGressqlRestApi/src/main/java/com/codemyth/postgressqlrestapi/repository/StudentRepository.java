package com.codemyth.postgressqlrestapi.repository;

import com.codemyth.postgressqlrestapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
