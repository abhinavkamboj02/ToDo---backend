package com.abhi.todo.services;

import com.abhi.todo.Dto.TodoDto;
import com.abhi.todo.Repository.TodoRepository;
import com.abhi.todo.exceptions.ResourceNotFoundException;
import com.abhi.todo.models.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TodoService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TodoRepository todoRepository;
    List<Todo> todos = new ArrayList<>();
    public TodoDto createTodo(TodoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }
    public List<TodoDto> getAllTodos() {
        List<Todo> allTodos = todoRepository.findAll();
        List<TodoDto> allTodosDto = allTodos.stream().map(todo -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
        return allTodosDto;
    }
    public TodoDto getTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("No Todo Found with given ID"));
        return modelMapper.map(todo, TodoDto.class);
    }
    public TodoDto updateTodo(int todoId, TodoDto todoDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("No Todo Found with given ID"));
        todo.setTitle(todoDto.getTitle());
        todo.setContent(todoDto.getContent());
        todo.setStatus(todoDto.getStatus());
        todo.setToDoDate(todoDto.getToDoDate());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
    }

}
