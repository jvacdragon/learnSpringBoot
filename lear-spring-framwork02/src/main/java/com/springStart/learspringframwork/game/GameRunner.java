package com.springStart.learspringframwork.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
     private GamingConsole game;
    public GameRunner(@Qualifier("superContraGame") GamingConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Runnin game: " + game);
    }
}
