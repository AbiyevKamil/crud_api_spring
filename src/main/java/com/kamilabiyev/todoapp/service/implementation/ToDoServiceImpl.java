package com.kamilabiyev.todoapp.service.implementation;

import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.mapping.abstraction.ToDoMapper;
import com.kamilabiyev.todoapp.persistence.entity.ToDo;
import com.kamilabiyev.todoapp.repository.ToDoRepository;
import com.kamilabiyev.todoapp.service.abstraction.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ToDoDTO add(ToDoDTO toDoDTOoDo) {
        ToDo model = mapper.mapToToDo(toDoDTOoDo);
        ToDoDTO toDoDTO = mapper.mapToToDoDTO(toDoRepository.save(model));
        return toDoDTO;
    }

    @Override
    public ToDoDTO update(ToDoDTO updatedToDoDTO) {
        ToDo model = mapper.mapToToDo(updatedToDoDTO);
        Optional<ToDo> exist = toDoRepository.findById(updatedToDoDTO.getId());
        if (exist.isPresent()) {
            ToDo item = exist.get();
            if (updatedToDoDTO.getIsCompleted() != null &&
                    !updatedToDoDTO.getIsCompleted().equals(item.getIsCompleted())) {
                item.setIsCompleted(updatedToDoDTO.getIsCompleted());
            }

            if (!"".equalsIgnoreCase(updatedToDoDTO.getTitle()) &&
                    !updatedToDoDTO.getTitle().equals(item.getTitle())) {
                item.setTitle(updatedToDoDTO.getTitle());
            }

            if (!"".equalsIgnoreCase(updatedToDoDTO.getContent()) &&
                    !updatedToDoDTO.getContent().equals(item.getContent())) {
                item.setContent(updatedToDoDTO.getContent());
            }

            return mapper.mapToToDoDTO(toDoRepository.save(item));
        }
        var toDoDTO = mapper.mapToToDoDTO(toDoRepository.save(model));
        return toDoDTO;
    }

    @Override
    public void remove(Integer id) {
        var toDo = toDoRepository.findById(id).get();
        toDo.setIsDeleted(true);
        toDoRepository.save(toDo);
    }
}
