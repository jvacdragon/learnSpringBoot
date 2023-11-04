package com.springStart.learspringframwork.examples.b1;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
class ClassA{}

@Component
@Lazy
class ClassB{

    ClassA classA;
    public ClassB(ClassA classA){
        this.classA = classA;
        System.out.println("Initialization");
        //Using @Lazy so the component only initialize when its really called
    }
}

@Configuration
//@ComponentScan("com.springStart.learspringframwork.examples")
@ComponentScan
public class LazyInictializationLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(LazyInictializationLauncherApplication.class)){

            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
        }
    }
}
