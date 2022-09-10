package com.kamilabiyev.todoapp.repository;

import com.kamilabiyev.todoapp.persistence.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findByTitleContaining(String title);

    List<ToDo> findAllByIsDeletedEquals(Boolean isDeleted);
}
