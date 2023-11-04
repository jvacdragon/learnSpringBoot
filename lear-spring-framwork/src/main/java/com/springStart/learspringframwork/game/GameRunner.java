package com.springStart.learspringframwork.game;

public class GameRunner {
     private GamingConsole game;
    public GameRunner(GamingConsole game) {
        this.game =game;
    }

    public void run() {
        System.out.println("Runnin game: " + game);
    }
}
