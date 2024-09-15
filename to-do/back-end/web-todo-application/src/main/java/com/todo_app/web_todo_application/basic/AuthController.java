package com.todo_app.web_todo_application.basic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @GetMapping(path = "/basicAuth")
    public String basicAuthCheck(){
        return "Success";
    }
}
