package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.TypeAnswer;

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
        if (numberOfRounds <= 3) {
            System.out.println("Якубович: приглашаю " + numberOfRounds + " тройку игроков: " + concatNames(takeNamePlayers(players)));
        } else {
            System.out.println("Якубович: приглашаю победителей групповых этапов: " + concatNames(takeNamePlayers(players)));
        }
    }

    private String concatNames(String[] names) {
        return String.join(", ", names);
    }

    //    Имена тройки игроков в массив, скорее всего будет в другом классе
    private String[] takeNamePlayers(Player[] players) {
        int size = players.length;
        String[] name = new String[size];
        for (int i = 0; i < size; i++) {
            name[i] = players[i].getName();
        }
        return name;
    }

    //  Условие 4.5
    public void askQuestion(String question) {
        System.out.println("Якубович: Внимание вопрос!\n" + question);
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
    public void checkAnswerPlayer(PlayerAnswer playerAnswer, String answer, Tableau tableau) {
        TypeAnswer typeAnswer = playerAnswer.getTypeAnswer();
        if (typeAnswer.equals(TypeAnswer.LETTER)) {
            checkLetter(answer, tableau);
        } else {
            checkWord(answer,tableau);
        }

        System.out.println("__________________________________");
    }

    private void checkLetter (String answer, Tableau tableau) {
        if (tableau.openLetter(answer)) {
            System.out.println("Якубович: Есть такая буква, откройте ее!");
        } else {
            System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
        }
    }

    private void checkWord (String answer, Tableau tableau) {
        if (tableau.openCorrectAnswer(answer)) {
            System.out.println("Якубович: " + answer + "! Абсолютно верно!");
//                TODO Понять как вызвать этот метод
//                nameWinner();
        } else {
            System.out.println("Якубович: Неверно! Следующий игрок!");

        }
    }
}
