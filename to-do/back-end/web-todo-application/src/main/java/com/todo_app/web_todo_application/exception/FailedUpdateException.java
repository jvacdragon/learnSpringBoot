package com.todo_app.web_todo_application.exception;

public class FailedUpdateException extends RuntimeException{
    public FailedUpdateException(String message) {
        super(message);
    }
}
