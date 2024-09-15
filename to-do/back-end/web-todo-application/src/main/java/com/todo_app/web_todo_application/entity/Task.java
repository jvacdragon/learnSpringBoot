package com.todo_app.web_todo_application.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDate;

@Entity
public class Task {
    @Size(min = 5, message = "Campo deve ter mais de 5 caracteres")
    private String description;
    private boolean done;
    @FutureOrPresent(message = "A data deve ser igual a data atual ou posterior")
    private LocalDate date;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Nonnull
    @JoinColumn(name="username")
    private User username;

    public Task(String description, boolean done, LocalDate date, User username) {
        this.description = description;
        this.done = done;
        this.date = date;
        this.username = username;
    }

    public Task(){};
    public Task(String description, boolean done, LocalDate date, long id, User username) {
        this.description = description;
        this.done = done;
        this.date = date;
        this.id = id;
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
}
