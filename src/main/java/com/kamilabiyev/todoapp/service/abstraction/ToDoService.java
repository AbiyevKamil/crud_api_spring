package com.kamilabiyev.todoapp.service.abstraction;

import com.kamilabiyev.todoapp.dto.ToDoDTO;

import java.util.List;

public interface ToDoService {
    List<ToDoDTO> getAll();

    List<ToDoDTO> getByTitleContaining(String title);

    ToDoDTO getById(Integer id);

    ToDoDTO add(ToDoDTO toDo);

    ToDoDTO update(ToDoDTO toDo);

    void remove(Integer id);
}
