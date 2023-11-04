package com.springStart.learspringframwork.examples.e1;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
@Named
class BusinessService{
    private DataService dataService;

    public BusinessService(DataService dataService) {
        this.dataService = dataService;
    }

    public DataService getDataService(){
        return dataService;
    }
    //@Autowired
    @Inject
    public void setDataService(DataService dataService) {
        System.out.println("using setter autowiring: " + dataService);
        this.dataService = dataService;
    }
}
//@Component
@Named
class DataService{}
@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)){

            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));

            System.out.println(context.getBean(BusinessService.class).getDataService());
        }
    }
}
