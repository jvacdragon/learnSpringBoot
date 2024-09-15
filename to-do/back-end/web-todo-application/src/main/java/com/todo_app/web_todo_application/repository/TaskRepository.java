package com.todo_app.web_todo_application.repository;

import com.todo_app.web_todo_application.entity.Task;
import com.todo_app.web_todo_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t from Task t WHERE t.username = :user ")
    List<Task> findByUser(@Param("user")User user);
}
