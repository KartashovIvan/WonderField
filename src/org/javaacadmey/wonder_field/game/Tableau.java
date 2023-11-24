package org.javaacadmey.wonder_field.game;

import java.util.Arrays;

public class Tableau {
    private String correctAnswer;
    private String[] letters;

    public void initTableau(String correct_answer) {
        this.correctAnswer = correct_answer;
        this.letters = new String[correct_answer.length()];
    }

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

    public void openCorrectAnswer(String answer) {
        checkField();

        if (answer.equalsIgnoreCase(correctAnswer)) {
            letters = correctAnswer.split("");
        }
    }

    public void openLetter(String answer) {
        checkField();

        int[] keyArray = similarCharacters(answer);
        if (keyArray.length > 0) {
            for (int i : keyArray) {
                letters[i] = answer;
            }
        } else {
            System.out.println("Нет такой буквы");
        }
    }

    private int[] similarCharacters(String letter) {
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

    public boolean checkUnknownLetters() {
        for (String letter : letters) {
            if (letter.equals("_")) {
                return true;
            }
        }
        return false;
    }

    private void checkField() {
        if (correctAnswer.isEmpty()) {
            throw new RuntimeException("Не задан правильный ответ");
        }
    }
}
