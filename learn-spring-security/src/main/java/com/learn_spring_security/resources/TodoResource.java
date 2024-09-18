package com.learn_spring_security.resources;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoResource {

    List<Todo> LIST_TODOS = new ArrayList<>( List.of(new Todo("tarefa 1", "joao"),
            new Todo("tarefa 2", "joao"), new Todo("tarefa 2", "joao2")));

    @GetMapping("/todos")
    public List<Todo> retrieveTodos(){
        return LIST_TODOS;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodosByUsername(@PathVariable String username){
        List<Todo> userTodo =  LIST_TODOS.stream().filter(t -> t.username().equals(username)).toList();
        return userTodo;
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo){
        LIST_TODOS.add(todo);
        return todo;
    }
}

record Todo(String description, String username){}
