package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.TypeAnswer;

public class Accountant {
    int beforeAnswer;

    private void setBeforeAnswer(int beforeAnswer) {
        this.beforeAnswer = beforeAnswer;
    }

    private int difference(int afterAnswer) {
        return afterAnswer - beforeAnswer;
    }

    public void countLetterBeforeAnswer(PlayerAnswer playerAnswer, Tableau tableau) {
        if (playerAnswer.getTypeAnswer() == TypeAnswer.LETTER) {
            setBeforeAnswer(tableau.countOpenLetters());
        }
    }

    public void countLetterAfterAnswer(PlayerAnswer playerAnswer, Player player, Tableau tableau) {
        if (playerAnswer.getTypeAnswer() == TypeAnswer.LETTER) {
            player.setCounter(player.getCounter() + difference(tableau.countOpenLetters()));
        }
    }
}
