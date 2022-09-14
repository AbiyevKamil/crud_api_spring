package com.kamilabiyev.todoapp.mapping.abstraction;


import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.persistence.entity.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDoMapper {

    ToDoDTO mapToToDoDTO(ToDo toDo);

    ToDo mapToToDo(ToDoDTO toDoDTO);

    List<ToDoDTO> mapToToDoDTO(List<ToDo> toDos);

    List<ToDo> mapToToDo(List<ToDoDTO> toDoDTOs);
}
