package com.springStart.learspringframwork.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
public class MarioGame implements GamingConsole{

    public void up(){
        System.out.println("Jump");
    }

    public void down(){
        System.out.println("Go into a hole!");
    }

    public void left(){
        System.out.println("Go to left!");
    }

    public void right(){
        System.out.println("Go to right!");
    }
}
