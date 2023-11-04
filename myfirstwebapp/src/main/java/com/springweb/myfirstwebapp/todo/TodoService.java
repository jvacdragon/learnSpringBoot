package com.springweb.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static int cont = 0;
    private static List<Todo> todos = new ArrayList<>();
    static {
        todos.add(new Todo(cont++, "joaovitor", "learn java", LocalDate.now(), false));
        todos.add(new Todo(cont++, "vitoraguiar", "learn springboot", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(cont++, "joao vitor", "learn javascrcipt", LocalDate.now().plusMonths(2), false));
    }
    public List<Todo> findByUsername(String username){
        Predicate<Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
    public Todo findTodoById(int id){
        Predicate <? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void addTodo(Todo todo){
        todos.size();
        todos.add(todo);
    }
    public void deleteById( int id){
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;
        todos.removeIf(predicate);
    }

    public void updateTodo(Todo todo){
        int index = todos.indexOf(findTodoById(todo.getId()));
        todos.remove(index);
        todos.add(index, todo);
    }
}
