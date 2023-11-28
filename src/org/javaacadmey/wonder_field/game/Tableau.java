package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.PlayerAnswer;

import java.util.Arrays;

public class Tableau {
    //  Условие 2.1
    private String correctAnswer;

    private String[] letters;

    //  Условие 2.2
    public void initTableau(Exercise exercise) {
        this.correctAnswer = exercise.getAnswer();
        this.letters = new String[correctAnswer.length()];
        for (int i = 0; i < correctAnswer.length(); i++) {
            letters[i] = "_";
        }
    }

    //  Условие 2.3
    public void showTableau() {
        checkField();

        StringBuilder word = new StringBuilder();
        for (String letter : letters) {
            if (letter == null) {
                word.append("_");
            } else {
                word.append(letter.toUpperCase());
            }
            word.append(" ");
        }
        System.out.println(word);
    }

    //  Условие 2.4
    public void openLetter(PlayerAnswer playerAnswer) {
        checkField();

        int[] numberOfLetters = similarCharacters(playerAnswer.getAnswer(), correctAnswer);
        for (int i : numberOfLetters) {
            letters[i] = playerAnswer.getAnswer();
        }
    }

    public boolean checkOpenLetters(PlayerAnswer playerAnswer) {
        for (String str : letters) {
            if (str.equalsIgnoreCase(playerAnswer.getAnswer())) {
                return true;
            }
        }
        return false;
    }

    private int[] similarCharacters(String letter, String correctAnswer) {
        int count = 0;
        int[] keyArray = new int[0];
        for (String value : correctAnswer.split("")) {
            if (value.equalsIgnoreCase(letter)) {
                keyArray = Arrays.copyOf(keyArray, keyArray.length + 1);
                keyArray[keyArray.length - 1] = count;
            }
            count++;
        }
        return keyArray;
    }

    //  Условие 2.5
    public void openCorrectAnswer(String answer) {
        letters = answer.split("");
    }

    //  Условие 2.6
    public boolean checkUnknownLetters() {
        for (String letter : letters) {
            if (letter.equals("_")) {
                return true;
            }
        }
        return false;
    }

    //  Условие 2.7
    private void checkField() {
        if (correctAnswer.isEmpty()) {
            throw new RuntimeException("Не задан правильный ответ на вопрос");
        }
    }
}
