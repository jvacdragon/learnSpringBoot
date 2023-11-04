package com.springweb.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.springweb.myfirstwebapp.todo.TodoRepository.*;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

    private TodoRepository todoRepository;

    public TodoControllerJPA(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value="list-todos")
    public String listAllTodos(ModelMap model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Todo> todos = todoRepository.findByUsername(getLoggedinUsername());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        todo.setFormattedDate(todo.getTargetDate().format(formatter));
        todo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        todoRepository.save(todo);
        //todoService.addTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value="toggle-boolean")
    public String toggleBooleanTodo(@RequestParam int id){
        Todo todo = todoRepository.findById(id).get();
        if (todo.isDone() == true) {
            todo.setDone(false);
        } else {
            todo.setDone(true);
        }
        todoRepository.save(todo);

        return"redirect:list-todos";
    }

    @RequestMapping(value="delete-todo")
    public String deleteTodo(@RequestParam int id){
        //todoService.deleteById(id);
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo")
    public String updateTodo(@RequestParam int id, ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String updateTodoPage(@RequestParam int id, ModelMap model) {
//        Todo todo = new Todo(id, getLoggedinUsername(), "", LocalDate.now(), false);
//        model.put("todo", todo);
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "addTodo";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        todo.setFormattedDate(todo.getTargetDate().format(formatter));
        todo.setUsername(getLoggedinUsername());
        //todoService.updateTodo(todo);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
