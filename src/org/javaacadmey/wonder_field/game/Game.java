package org.javaacadmey.wonder_field.game;

import java.util.Random;
import java.util.Scanner;
import org.javaacadmey.wonder_field.domain.Box;
import org.javaacadmey.wonder_field.domain.Exercise;
import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;

public class Game {
    public static final Scanner READER = new Scanner(System.in);

    private final int numberOfPlayers = 3;
    private final int numberOfRounds = 5;
    private final int numberOfGroupRounds = 3;
    private final Exercise[] exercises = new Exercise[numberOfRounds];
    private final Tableau tableau = new Tableau();
    private final Yakubovich yakubovich = new Yakubovich();
    private final Player[] winners = new Player[numberOfPlayers];
    private final Wheel wheel = new Wheel();
    private final Accountant accountant = new Accountant();
    private Player winner;

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

        System.out.println("\n".repeat(50));
    }

    private void createQuestion() {
//        Закоментировать, если не нужно вводить вопросы при каждом старте игры
//        for (int i = 0; i < numberOfRounds; i++) {
//            int number = i + 1;
//            System.out.println("Введите вопрос #" + number);
//            String question = readConsole();
//            System.out.println("Введите ответ вопрос #" + number);
//            String answers = readConsole();
//            exercises[i] = new Exercise(question, answers);
//        }

//      Закоментировать если нужно добавлять вопросы в ручную при старте игры
        autoCreateQuestion();
    }

    private void autoCreateQuestion() {
        exercises[0] = new Exercise("Как называется третья планета от солнца?", "Земля");
        exercises[1] = new Exercise("Какого слова не хватает во фразе \"Пейте, дети, ... будете здоровыми!\" ?", "молоко");
        exercises[2] = new Exercise("Как зовут ведущего?", "Якубович");
        exercises[3] = new Exercise("Как называется последняя планета в солнечной системе?", "нептун");
        exercises[4] = new Exercise("В словаре Владимира Ивановича Даля встречается старинное название барометра. Какое?", "Буревестник");
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
            System.out.println("Игрок №" + number + " представьтесь: имя,город. Например: Иван,Москва");
            String[] nameAndCity = readConsole().split(",");
            players[i] = new Player(nameAndCity[0].trim(), nameAndCity[1].trim());
        }

        return players;
    }

    //    Пункт 5.3
    public static String[] takeNamePlayers(Player[] players) {
        String[] name = new String[3];
        for (int i = 0; i < 3; i++) {
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
        System.out.println("Ход игрока " + player.getCity() + ", " + player.getCity());
        if (checkWheel(player)) {
            return false;
        }

        PlayerAnswer playerAnswer = player.move();
        accountant.countLetterBeforeAnswer(playerAnswer, tableau);
        if (yakubovich.checkAnswerPlayer(playerAnswer, exercise, tableau)) {
            accountant.countLetterAfterAnswer(playerAnswer, player, tableau);
            return true;
        }
        return false;
    }

    private boolean checkWheel(Player player) {
        int gamePoint = wheel.spinWheel();
        if (gamePoint == 14) {
            yakubovich.skipPlayer();
            return true;
        }
        wheel.givePoints(player, gamePoint);
        return false;
    }

    //    Пункт 5.6
    private boolean playRound(Exercise exercise, Player player) {
        while (moveOfPlayer(exercise, player)) {
            checkPlayerCounter(player);
            if (openAllLetter()) {
                player.setCounter(0);
                return true;
            }
        }
        player.setCounter(0);
        return false;
    }

    private void checkPlayerCounter(Player player) {
        if (player.getCounter() >= 3) {
            chooseBox(player, createBoxes());
            player.setCounter(player.getCounter() - 3);
        }
    }

    private void chooseBox(Player player, Box[] boxes) {
        System.out.println("После отгаданных 3-х букв подряд необходимо выбрать одну из коробок. Какую коробку выбираете? Если левую введите 'л', если правую введите 'п'");
        boolean end = true;
        while (end) {
            String answer = Game.readConsole();
            switch (answer) {
                case ("л") -> {
                    checkBox(player, boxes[0]);
                    end = false;
                }
                case ("п") -> {
                    checkBox(player, boxes[1]);
                    end = false;
                }
                default -> System.out.println("Некорректное значение, введите 'л' или 'п'");
            }
        }
    }

    private void checkBox(Player player, Box box) {
        int money = box.getMoney();
        if (money > 0) {
            player.setMoney(player.getMoney() + money);
            System.out.println("И вы получаете приз " + money + " рублей");
        } else {
            System.out.println("К сожалению деньги были в другой коробке");
        }
    }

    private Box[] createBoxes() {
        Random random = new Random();
        Box[] boxes = new Box[]{new Box(), new Box()};

        boxes[random.nextInt(0, 2)].setMoney(random.nextInt(0, 10_001));

        return boxes;
    }

    //    Пункт 5.7
    private void playGroupRounds() {
        for (int i = 0; i < numberOfGroupRounds; i++) {
            Player[] players = createPlayers();

            Exercise exercise = exercises[i];
            tableau.initTableau(exercise);
            yakubovich.invitePlayers(players, i);
            yakubovich.askQuestion(exercise);

            boolean play = true;
            while (play) {
                for (Player player : players) {
                    if (playRound(exercise, player)) {
                        yakubovich.nameWinner(player, i);
                        addWinner(i, player);
                        play = false;
                        break;
                    }
                    tableau.showTableau();
                }
            }
        }
    }

    private void playFinalGroupRounds() {
        Exercise exercise = exercises[numberOfGroupRounds];
        tableau.initTableau(exercise);
        yakubovich.invitePlayers(winners, numberOfGroupRounds);
        yakubovich.askQuestion(exercise);

        boolean play = true;
        while (play) {
            for (Player player : winners) {
                tableau.showTableau();
                if (playRound(exercise, player)) {
                    tableau.showTableau();
                    yakubovich.nameWinner(player, numberOfRounds - 2);
                    winner = player;
                    play = false;
                    break;
                }
            }
        }
    }

    public void startGame() {
        yakubovich.startGame();
        playGroupRounds();
        playFinalGroupRounds();
        SuperGame superGame = new SuperGame();
        superGame.initSuperGame(winner, exercises[numberOfRounds - 1], yakubovich, tableau);
        yakubovich.nameWinner(winner, numberOfRounds);
        yakubovich.endGame();
    }
}

