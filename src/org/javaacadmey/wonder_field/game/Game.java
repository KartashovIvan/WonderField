package org.javaacadmey.wonder_field.game;


import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;

import java.util.Scanner;

public class Game {
    private final int numberOfPlayers = 3;

    private final int numberOfRounds = 4;

    private final int numberOfGroupRounds = 3;

    private final Exercise[] exercises = new Exercise[numberOfRounds];

    private final Tableau tableau = new Tableau();

    private final Yakubovich yakubovich = new Yakubovich();

    private final Player[] winners = new Player[numberOfPlayers];

    public static final Scanner READER = new Scanner(System.in);

    public static String readConsole() {
        return READER.nextLine();
    }

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
        startGame();
    }

    private void createQuestion() {
//        Закоментировать, если не нужно вводить вопросы при каждом старте игры
        for (int i = 0; i < numberOfRounds; i++) {
            int number = i + 1;
            System.out.println("Введите вопрос #" + number);
            String question = readConsole();
            System.out.println("Введите ответ вопрос #" + number);
            String answers = readConsole();
            exercises[i] = new Exercise(question, answers);
        }

//      Закоментировать если нужно добавлять вопросы в ручную при старте игры
//        exercises[0] = new Exercise("Как называется третья планета от солнца?", "земля");
//        exercises[1] = new Exercise("Какого слова не хватает во фразе \"Пейте, дети, ... будете здоровыми!\" ?", "молоко");
//        exercises[2] = new Exercise("Как зовут ведущего?", "Якубович");
//        exercises[3] = new Exercise("Как называется последняя планета в солнечной системе?", "Нептун");
    }

    //    Пункт 5.1
    private void addWinner(int numberOfRounds, Player player) {
        winners[numberOfRounds] = player;
    }

    //    Пункт 5.2
    private Player[] createPlayers() {
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
    private boolean openAllLetter() {
        return !tableau.checkUnknownLetters();
    }

    //    Пункт 5.5
    private boolean moveOfPlayer(Exercise exercise, Player player) {
        PlayerAnswer playerAnswer = player.move();
        return yakubovich.checkAnswerPlayer(playerAnswer, exercise, tableau);
    }

    //    Пункт 5.6
    private boolean playRound(Exercise exercise, Player player) {
        while (moveOfPlayer(exercise, player)) {
            if (openAllLetter()) {
                return true;
            }
        }
        return false;
    }

    //    Пункт 5.7
    private void playGroupRounds() {
        for (int i = 0; i < numberOfGroupRounds; i++) {
            Player[] players = createPlayers();

            Exercise exercise = exercises[i];
            tableau.initTableau(exercise);
            yakubovich.invitePlayers(players, i);
            yakubovich.askQuestion(exercise);
//            tableau.showTableau();

            boolean play = true;
            int countMove = 0;
            while (play) {
                if (playRound(exercise, players[countMove])) {
                    yakubovich.nameWinner(players[countMove], i);
                    addWinner(i, players[countMove]);
                    play = false;
                }
                tableau.showTableau();
                countMove = countMove + 1;

                if (countMove == 3) {
                    countMove = 0;
                }
            }
        }
    }

    private void playFinalGroupRounds(Player[] players) {
        Exercise exercise = exercises[numberOfGroupRounds];
        tableau.initTableau(exercise);
        yakubovich.invitePlayers(winners, numberOfGroupRounds);
        yakubovich.askQuestion(exercise);

        boolean play = true;
        int countMove = 0;
        while (play) {
            if (playRound(exercise, players[countMove])) {
                yakubovich.nameWinner(players[countMove], numberOfGroupRounds);
                play = false;
            }
            tableau.showTableau();
            countMove = countMove + 1;

            if (countMove == 3) {
                countMove = 0;
            }
        }
    }

    private void startGame() {
        yakubovich.startGame();
        playGroupRounds();
        playFinalGroupRounds(winners);
        yakubovich.endGame();
    }
}

