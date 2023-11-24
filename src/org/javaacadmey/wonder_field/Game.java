package org.javaacadmey.wonder_field;

import java.util.Scanner;

public class Game {
    public static final Scanner READER = new Scanner(System.in);

    private final int numberOfPlayers = 3;
    private final int numberOfRounds = 4;
    private final int numberOfGroupRounds = 3;
    private final String[] question = new String[numberOfRounds];
    private final String[] answers = new String[numberOfRounds];
    private final Tableau tableau = new Tableau();

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
//        for (int i = 0; i < numberOfRounds; i++) {
//            System.out.println("Введите вопрос #" + (i + 1));
//            question[i] = reader.nextLine();
//            System.out.println("Введите ответ вопрос #" + (i + 1));
//            answers[i] = reader.nextLine();
//        }
        question[0] = "Как называется третья планета от солнца?";
        question[1] = "Вопрос 2";
        question[2] = "Вопрос 3";
        question[3] = "Вопрос 4";

        answers[0] = "Земля";
        answers[1] = "Ответ 2";
        answers[2] = "Ответ 3";
        answers[3] = "Ответ 4";

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        for (int i = 0; i < question.length; i++) {
            tableau.initTableau(answers[i]);
        }
    }
}
