package com.todo_app.web_todo_application.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank(message = "Por favot insira um nome válido") String username,
                      @NotBlank(message = "Favor inserir senha válida") String password) {
}
