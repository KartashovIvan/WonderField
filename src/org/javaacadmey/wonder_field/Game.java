package org.javaacadmey.wonder_field;

import java.util.Scanner;

public class Game {

    private final int NUMBER_OF_PLAYERS = 3;
    private final int NUMBER_OF_ROUNDS = 4;
    private final int NUMBER_OF_GROUP_ROUNDS = 3;
    private final String[] QUESTION = new String[NUMBER_OF_ROUNDS];
    private final String[] ANSWERS = new String[NUMBER_OF_ROUNDS];
    static final Scanner READER = new Scanner(System.in);
    private final Tableau TABLEAU = new Tableau();

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
//        for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
//            System.out.println("Введите вопрос #" + (i + 1));
//            QUESTION[i] = READER.nextLine();
//            System.out.println("Введите ответ вопрос #" + (i + 1));
//            ANSWERS[i] = READER.nextLine();
//        }
        QUESTION[0] = "Как называется третья планета от солнца?";
        QUESTION[1] = "Вопрос 2";
        QUESTION[2] = "Вопрос 3";
        QUESTION[3] = "Вопрос 4";

        ANSWERS[0] = "Земля";
        ANSWERS[1] = "Ответ 2";
        ANSWERS[2] = "Ответ 3";
        ANSWERS[3] = "Ответ 4";

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        for (int i = 0; i < QUESTION.length; i++) {
            TABLEAU.initTableau(ANSWERS[i]);
        }
    }
}
