package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.TypeAnswer;

import java.util.Arrays;

import static org.javaacadmey.wonder_field.game.Game.takeNamePlayers;

public class Yakubovich {
    //  Условие 4.2
    public void startGame() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    //  Условие 4.3
    public void endGame() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    //  Условие 4.4
    public void invitePlayers(Player[] players, int numberOfRounds) {
        if (numberOfRounds < 3) {
            System.out.println("Якубович: приглашаю " + (numberOfRounds + 1) + " тройку игроков: " + concatNames(players));
        } else {
            System.out.println("Якубович: приглашаю победителей групповых этапов: " + concatNames(players));
        }
    }

    private String concatNames(Player[] players) {
        return String.join(", ", takeNamePlayers(players));
    }

    //  Условие 4.5
    public void askQuestion(Exercise exercise) {
        System.out.println("Якубович: Внимание вопрос!\n" + exercise.getQuestion());
    }

    //  Условие 4.6
    public void nameWinner(String name, String city, int numberOfRounds) {
        if (numberOfRounds <= 3) {
            System.out.println("Якубович: Молодец! " + name + " из " + city + " проходит в финал!");
        } else {
            System.out.println("Якубович: И перед нами победитель Капитал шоу поле чудес! Это " + name + " из " + city);
        }
    }

    //  Условие 4.7
    public void checkAnswerPlayer(PlayerAnswer playerAnswer, Exercise exercise, Tableau tableau) {
        String answerPlayer = playerAnswer.getAnswer();
        String correctAnswer = exercise.getAnswer();

        if (playerAnswer.getTypeAnswer().equals(TypeAnswer.LETTER)) {
            checkLetter(answerPlayer, correctAnswer, tableau);
        } else {
            checkWord(answerPlayer, correctAnswer, tableau);
//            TODO: как вызвать этот метод от сюда , нужно ли это тут делать, если да, то как передать сюда номер раунда
//            nameWinner(playerAnswer.getPlayer().getName(), playerAnswer.getPlayer().getCity());
        }

        System.out.println("__________________________________");
    }

    private void checkLetter(String answerPlayer, String correctAnswer, Tableau tableau) {
        if (correctAnswer.contains(answerPlayer)) {
            System.out.println("Якубович: Есть такая буква, откройте ее!");
            int[] letters = similarCharacters(answerPlayer, correctAnswer);
            tableau.openLetter(letters, answerPlayer);
        } else {
            System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
        }
    }

    private int[] similarCharacters(String letter, String correctAnswer) {
        int count = 0;
        int[] keyArray = new int[1];
        for (String value : correctAnswer.split("")) {
            if (value.equalsIgnoreCase(letter)) {
                keyArray = Arrays.copyOf(keyArray, keyArray.length + 1);
                keyArray[keyArray.length - 1] = count;
            }
            count++;
        }
        return keyArray;
    }

    private void checkWord(String answerPlayer, String correctAnswer, Tableau tableau) {
        if (answerPlayer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Якубович: " + answerPlayer + "! Абсолютно верно!");
            tableau.openCorrectAnswer(answerPlayer);
        } else {
            System.out.println("Якубович: Неверно! Следующий игрок!");
        }
    }
}
