package com.springStart.learspringframwork.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age){};
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String name(){
        return "Joao";
    }
    @Bean
    public int age(){
        return 20;
    }
    @Bean
    public int age2(){
        return 16;
    }
    @Bean
    public Person person(){
        var person = new Person(name(), age());
        return person;
    }
    @Bean
    @Primary
    public Person person2(String name, int age2){
        return new Person(name, age2);
    }
}
