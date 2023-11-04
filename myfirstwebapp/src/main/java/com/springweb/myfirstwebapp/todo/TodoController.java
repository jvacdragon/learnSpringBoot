package com.springweb.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.naming.Binding;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value="list-todos")
    public String listAllTodos(ModelMap model){
        List<Todo> todos = todoService.findByUsername(getLoggedinUsername());
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String addTodoPage(ModelMap model) {
        Todo todo = new Todo(0, getLoggedinUsername(), "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }
    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }
        todo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        todoService.addTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value="delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo")
    public String updateTodo(@RequestParam int id, ModelMap model){
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String updateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = new Todo(id, getLoggedinUsername(), "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }
        todo.setUsername(getLoggedinUsername());
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
