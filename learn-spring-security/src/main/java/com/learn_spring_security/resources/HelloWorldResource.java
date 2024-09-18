package com.learn_spring_security.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @GetMapping("/helloworld")
    public String HelloWorld(){
        return "Hello World";
    }
}
