package com.springStart.learspringframwork.examples.a1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class YourBusinessApplication{
    @Autowired
    Dependency1 dependency;
    Dependency2 dependency2;
    //@Autowired
    public YourBusinessApplication(Dependency1 dependency, Dependency2 dependency2){
        this.dependency = dependency;
        this.dependency2 = dependency2;
        System.out.println("Dependency 1 Constructor Injection = " + dependency + "\nDependency 2 Constructor Injection= " +dependency2);
    }
    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        this.dependency2 = dependency2;
    }

    public String toString(){
        return "Dependency 1 Field Injection = " + dependency + "\nDependency 2 Setter Injection= " +dependency2;
    }
}
@Component
class Dependency1{}
@Component
class Dependency2{}
@Configuration
//@ComponentScan("com.springStart.learspringframwork.examples")
@ComponentScan
public class DepInjectionLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)){

            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));

            //System.out.println(context.getBean(YourBusinessApplication.class).toString());
            context.getBean((YourBusinessApplication.class));

        }
    }
}
