package org.javaacadmey.wonder_field;

import java.util.Arrays;

public class Tableau {
    private String CORRECT_ANSWER;
    private String[] LETTERS;

    public void initTableau(String correct_answer) {
        this.CORRECT_ANSWER = correct_answer;
        this.LETTERS = new String[correct_answer.length()];
    }

    public void showTableau() {
        checkField();

        StringBuilder word = new StringBuilder();
        for (String letter : LETTERS) {
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

        if (answer.equalsIgnoreCase(CORRECT_ANSWER)) {
            LETTERS = CORRECT_ANSWER.split("");
        }
    }

    public void openLetter(String answer) {
        checkField();

        int[] keyArray = similarCharacters(answer);
        if (keyArray.length > 0) {
            for (int i : keyArray) {
                LETTERS[i] = answer;
            }
        } else {
            System.out.println("Нет такой буквы");
        }
    }

    private int[] similarCharacters(String letter) {
        int count = 0;
        int[] keyArray = new int[0];
        for (String value : CORRECT_ANSWER.split("")) {
            if (value.equalsIgnoreCase(letter)) {
                keyArray = Arrays.copyOf(keyArray, keyArray.length + 1);
                keyArray[keyArray.length - 1] = count;
            }
            count++;
        }
        return keyArray;
    }

    public boolean checkUnknownLetters() {
        for (String letter : LETTERS) {
            if (letter.equals("_")) return true;
        }
        return false;
    }

    private void checkField() {
        if (CORRECT_ANSWER.isEmpty()) {
            throw new RuntimeException("Не задан правильный ответ");
        }
    }
}
