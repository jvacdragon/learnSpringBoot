package com.todo_app.web_todo_application.controller;

import com.todo_app.web_todo_application.dto.TaskDTO;
import com.todo_app.web_todo_application.entity.Task;
import com.todo_app.web_todo_application.entity.User;
import com.todo_app.web_todo_application.exception.*;
import com.todo_app.web_todo_application.repository.TaskRepository;
import com.todo_app.web_todo_application.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users/{username}/todos")
    public List<Task> retrieveUserTasks(@PathVariable String username){
        if(userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst().isEmpty()){
            throw new UserNotFoundException("Usuário: " +username+ " não encontrado");
        }
        Optional<User> o = userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst();

        return taskRepository.findByUser(o.get());
    }

    @GetMapping(path = "/users/{username}/todos/{todoId}")
    public Task retrieveUserTasks(@PathVariable String username, @PathVariable int todoId){
        if(userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst().isEmpty()){
            throw new UserNotFoundException("Usuário: " +username+ " não encontrado");
        }
        Optional<User> o = userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst();

        if(taskRepository.findByUser(o.get()).stream().filter(t -> t.getId() == todoId).findFirst().isEmpty()){
            throw new TaskNotFoundException("Tarefa: " + todoId + " não encontrada");
        };
        Optional<Task> task = taskRepository.findByUser(o.get()).stream().filter(t -> t.getId() == todoId).findFirst();
        return task.get();
    }

    @PutMapping(path = "users/{username}/todos/{todoId}")
    public Task updateTodo(@Valid @RequestBody Task task, @PathVariable String username, @PathVariable int todoId){
        try{
            if(userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst().isEmpty()){
                throw new UserNotFoundException("Usuário: " +username+ " não encontrado");
            }
            User user = userRepository.findByUsername(username);
            task.setId(todoId);
            task.setUsername(user);
            taskRepository.save(task);
            return task;

        }catch (Exception e){
            throw new FailedUpdateException("Falha ao atualizar tarefa: " + e.getMessage());
        }
    }

    @PostMapping(path = "users/{username}/todos")
    public ResponseEntity<Task> postTask(@PathVariable String username, @Valid @RequestBody TaskDTO taskDTO){

            if(userRepository.findAll().stream().filter(u -> username.equals(u.getUsername())).findFirst().isEmpty()){
                throw new UserNotFoundException("Usuário: " + username + " não encontrado. Não foi possível adicionar tarefa.");
            }
            var task = new Task(taskDTO.description(), taskDTO.done(), taskDTO.date(), taskDTO.username());
            var t = taskRepository.save(task);
            return ResponseEntity.ok(t);
    }

    @DeleteMapping(path = "users/{username}/todos/{todoId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String username, @PathVariable Long todoId){
        try{
            taskRepository.deleteById(todoId);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            throw new DeleteException("Não foi possível deletar esta tarefa");
        }
    }
}
