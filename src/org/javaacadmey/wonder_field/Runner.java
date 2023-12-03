package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.game.Game;

public class Runner {
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.startGame();
    }
}
