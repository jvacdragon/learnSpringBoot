package com.springStart.learspringframwork.exercise;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;

interface DataService{
    int[] retrieveData();
}
@Component
class MongoDbService implements  DataService{
    public int[] retrieveData(){
        return new int[] {11,22,33,44,55};
    }
}
@Component
@Primary
class MySQLDataService implements  DataService{
    public int[] retrieveData(){
        return new int[] {1,2,3,4,5};
    }
}
@Component
class BusinessCalculationService{
    private DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        this.dataService = dataService;
    }

    public int findMax(){
        return Arrays.stream(dataService.retrieveData()).max().orElse(0);
    }
}
@Configuration
@ComponentScan
public class Exercise {
    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(Exercise.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }
    }
}
