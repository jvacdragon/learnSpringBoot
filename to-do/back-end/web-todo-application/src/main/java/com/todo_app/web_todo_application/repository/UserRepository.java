package com.todo_app.web_todo_application.repository;

import com.todo_app.web_todo_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u from User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
