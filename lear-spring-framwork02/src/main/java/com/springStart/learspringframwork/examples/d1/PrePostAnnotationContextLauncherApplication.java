package com.springStart.learspringframwork.examples.d1;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass{
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency) {
        this.someDependency = someDependency;
        System.out.println("Dependencies are ready");
    }
    @PostConstruct
    public void initializer(){
        //can be used for init a connection with the database
        someDependency.getReady();
    }

    @PreDestroy
    public void cleanup(){
        System.out.println("you can use for end the connection with the database");
    }
}

@Component
class SomeDependency{
    void getReady(){
        System.out.println("Get ready dependency after initialization of parent");
    }
}

@Configuration
@ComponentScan
public class PrePostAnnotationContextLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(PrePostAnnotationContextLauncherApplication.class)){

            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
        }
    }
}
