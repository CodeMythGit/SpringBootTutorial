package com.example.springbootinterview.repository;

import com.example.springbootinterview.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    List<Student> findByFirstName(@Param("firstName") String firstName);
}
