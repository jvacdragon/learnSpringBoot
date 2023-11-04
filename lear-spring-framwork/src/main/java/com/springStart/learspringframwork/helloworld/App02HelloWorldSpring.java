package com.springStart.learspringframwork.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);){
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean(Person.class));
            System.out.println(context.getBean("person"));
            Arrays.stream(context.getBeanDefinitionNames()).forEach(print -> System.out.println(print));
            //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }

    }
}
