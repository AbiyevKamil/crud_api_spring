package com.kamilabiyev.todoapp.service.implementation;

import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.mapping.abstraction.ToDoMapper;
import com.kamilabiyev.todoapp.persistence.entity.ToDo;
import com.kamilabiyev.todoapp.repository.ToDoRepository;
import com.kamilabiyev.todoapp.service.abstraction.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    // MapStruct can be used also
    private final ToDoMapper mapper;


    @Override
    public List<ToDoDTO> getAll() {
        List<ToDoDTO> model = mapper.mapToToDoDTO(toDoRepository.findAllByIsDeletedEquals(false));
        return model;
    }

    @Override
    public List<ToDoDTO> getByTitleContaining(String title) {
        List<ToDoDTO> model = mapper.mapToToDoDTO(toDoRepository.findByTitleContaining(title));
        return model;
    }

    @Override
    public ToDoDTO getById(Integer id) {
        ToDoDTO model = mapper.mapToToDoDTO(toDoRepository.findById(id).orElse(null));
        return model;
    }

    @Override
    public ToDoDTO add(ToDoDTO toDo) {
        ToDo model = mapper.mapToToDo(toDo);
        toDo = mapper.mapToToDoDTO(toDoRepository.save(model));
        return toDo;
    }

    @Override
    public ToDoDTO update(ToDoDTO toDo) {
        ToDo model = mapper.mapToToDo(toDo);
        var toDoDTO = mapper.mapToToDoDTO(toDoRepository.save(model));
        return toDoDTO;

    }

    @Override
    public void remove(Integer id) {
        toDoRepository.findById(id).ifPresent(toDoRepository::delete);
    }
}
