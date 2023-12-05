package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.Player;

import java.util.Random;

public class Wheel {
    private final Random random = new Random();

    public int spinWheel() {
        return random.nextInt(1, 15);
    }

    public void givePoints(Player player, int gamePoint) {
        if (gamePoint < 13) {
            player.addGamePoints(gamePoint * 100);
        } else {
            player.addGamePoints(player.getGamePoints());
        }
    }


}
