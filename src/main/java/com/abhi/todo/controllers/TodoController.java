package com.abhi.todo.controllers;

import com.abhi.todo.Dto.TodoDto;
import com.abhi.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    Random random = new Random();
    @Autowired
    private TodoService todoService;

    //create
    @PostMapping
    public ResponseEntity<TodoDto> createTodoHandler(@RequestBody TodoDto todoDto) {
        Date currentDate = new Date();
        todoDto.setAddedDate(currentDate);
        logger.info("Create Todo");
        TodoDto todo1 = todoService.createTodo(todoDto);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodoHandler() {
        List<TodoDto> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getSingleTodoHandler(@PathVariable int todoId) {
        TodoDto todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodoHandler(@RequestBody TodoDto todoDtoWithNewDetails, @PathVariable int todoId) {
        TodoDto todo = todoService.updateTodo(todoId, todoDtoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    //
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Successfully deleted");

    }


}
