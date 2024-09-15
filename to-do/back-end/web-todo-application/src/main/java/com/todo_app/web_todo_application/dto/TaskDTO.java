package com.todo_app.web_todo_application.dto;

import com.todo_app.web_todo_application.entity.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskDTO (
        @NotBlank(message = "Descrição não pode estar em branco")
        @Size(min = 5, message = "Descrição deve ter pelo menos 5 caracteres")
        String description,
        @FutureOrPresent(message = "Data deve ser presente ou futuro")
        LocalDate date,
        boolean done, User username
){}
