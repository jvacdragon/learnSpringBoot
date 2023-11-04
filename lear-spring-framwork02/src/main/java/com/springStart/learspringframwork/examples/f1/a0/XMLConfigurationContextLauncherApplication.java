package com.springStart.learspringframwork.examples.f1.a0;

import com.springStart.learspringframwork.game.GameRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

//CREATE AN XML FILE IN RESOURCES
//TO SUBSTITUTE @COMPONENT:
//@SERVICE -> WHEN THE COMPONENT IS FOR BUSINESS LOGIC
//@CONTROLLER -> WHEN THE COMPONENT IS A CONTROLLER
//@REPOSITORY -> WHEN THE COMPONENT IS CONNECTING AND MANIPULATING DATA IN A DATABASE
public class XMLConfigurationContextLauncherApplication {
    public static void main(String[] args) {

        try(var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
            System.out.println(context.getBean("age"));
            context.getBean(GameRunner.class).run();
        }
    }
}
