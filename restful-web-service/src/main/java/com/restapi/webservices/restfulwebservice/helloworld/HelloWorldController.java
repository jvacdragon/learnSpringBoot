package com.restapi.webservices.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping(path="/hello-world-list")
    public List<String> helloWorldLis(){
        List<String> a = new ArrayList<>();
        a.add("Hello");
        a.add("World");
        return a;
    }
    @GetMapping(path="/hello-world-string")
    public String helloWorldString(){
        return "Hello World";
    }

    @GetMapping(path="hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean HelloWorldPath(@PathVariable String name){
        return new HelloWorldBean("the name is: " + name);
    }
}
