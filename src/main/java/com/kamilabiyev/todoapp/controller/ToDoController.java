package com.kamilabiyev.todoapp.controller;


import com.kamilabiyev.todoapp.dto.ToDoDTO;
import com.kamilabiyev.todoapp.service.abstraction.ToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
//@Api("ToDo controller")
public class ToDoController {

    private final ToDoService toDoService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
//    @ApiOperation(value = "Get all.", notes = "Get all todos.")
    public List<ToDoDTO> getAll() {
        return toDoService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    @ApiOperation(value = "Get by id.", notes = "Get a todo by id.")
    public ToDoDTO getById(@PathVariable(required = true) Integer id) {
        return toDoService.getById(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ToDoDTO add(@RequestBody(required = true) ToDoDTO toDoDTO) {
        return toDoService.add(toDoDTO);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ToDoDTO update(@RequestBody(required = true) ToDoDTO toDoDTO) {
        return toDoService.update(toDoDTO);
    }
}
