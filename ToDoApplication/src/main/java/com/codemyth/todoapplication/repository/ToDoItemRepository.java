package com.codemyth.todoapplication.repository;

import com.codemyth.todoapplication.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Integer> {
}
