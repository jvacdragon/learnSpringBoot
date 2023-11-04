package com.springStart.learspringframwork.game;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.springStart.learspringframwork.game")
public class GamingAppLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)){
            context.getBean(PacManGame.class).up();
            System.out.println(context.getBean( "marioGame"));
            context.getBean(GameRunner.class).run();

        }
    }
}
