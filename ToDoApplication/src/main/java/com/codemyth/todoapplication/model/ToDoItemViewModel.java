package com.codemyth.todoapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoItemViewModel {

    private List<ToDoItem> todoList = new ArrayList<>();

    public ToDoItemViewModel(Iterable<ToDoItem> list) {
        list.forEach(todoList::add);
    }
}
