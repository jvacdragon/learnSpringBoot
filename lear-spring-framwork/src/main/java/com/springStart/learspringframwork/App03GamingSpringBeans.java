package com.springStart.learspringframwork;


import com.springStart.learspringframwork.game.GameRunner;
import com.springStart.learspringframwork.game.GamingConsole;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)){
            context.getBean(GameRunner.class).run();

        }
    }
}
