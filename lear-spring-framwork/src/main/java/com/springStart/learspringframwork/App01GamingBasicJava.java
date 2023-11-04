package com.springStart.learspringframwork;

import com.springStart.learspringframwork.game.GameRunner;
import com.springStart.learspringframwork.game.MarioGame;
import com.springStart.learspringframwork.game.PacManGame;
import com.springStart.learspringframwork.game.SuperContraGame;

public class App01GamingBasicJava {
    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        var pacManGame = new PacManGame();
        var gameRunner = new GameRunner(pacManGame);

        gameRunner.run();
    }
}
