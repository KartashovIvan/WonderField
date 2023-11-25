package org.javaacadmey.wonder_field.game;


import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;

import static org.javaacadmey.wonder_field.utils.GameLogics.*;


public class Game {

    private final int numberOfPlayers = 3;
    private final int numberOfRounds = 4;
    private final int numberOfGroupRounds = 3;
    private final Exercise[] exercises = new Exercise[numberOfRounds];
    private final Tableau tableau = new Tableau();
    private final Yakubovich yakubovich = new Yakubovich();
    private final Player[] winners = new Player[numberOfPlayers];

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");

        createQuestion();

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        yakubovich.startGame();

        for (int i = 0; i <= numberOfGroupRounds; i++) {
            Player[] players = createPlayers();
            String[] namePlayers = takeNamePlayers(players);
        }
    }

    private void createQuestion() {
//        for (int i = 0; i < numberOfRounds; i++) {
//            int number = i + 1;
//            System.out.println("Введите вопрос #" + number);
//            String question = readConsole();
//            System.out.println("Введите ответ вопрос #" + number);
//            String answers = readConsole();
//            exercises[i] = new Exercise(question,answers);
//        }

        exercises[0] = new Exercise("Как называется третья планета от солнца?", "Земля");
        exercises[1] = new Exercise("Вопрос 2", "Ответ 2");
        exercises[2] = new Exercise("Вопрос 3", "Ответ 3");
        exercises[3] = new Exercise("Вопрос 4", "Ответ 4");
    }

    //    Пункт 5.1
    public void addWinner(int numberOfRounds, Player player) {
        winners[numberOfRounds] = player;
    }

    //    Пункт 5.2
    public Player[] createPlayers() {
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int number = i + 1;
            System.out.println("Игрок №" + number + " представьтесь: как вас зовут?");
            String name = readConsole();
            System.out.println("Откуда вы к нам приехали?");
            String city = readConsole();
            players[i] = new Player(name, city);
        }

        return players;
    }

    //    Пункт 5.3
    public static String[] takeNamePlayers(Player[] players) {
        int size = players.length;
        String[] name = new String[size];
        for (int i = 0; i < size; i++) {
            name[i] = players[i].getName();
        }
        return name;
    }

    //    Пункт 5.4
    public boolean checkTableau() {
        if (tableau.checkUnknownLetters()) {
            return false;
        }
        return true;
    }

    //    Пункт 5.5
//    public boolean moveOfPlayer (Exercise exercise, Player player){
//        PlayerAnswer an = player.move();
//        yakubovich.checkAnswerPlayer(an,exercise,tableau);
//    }
}
