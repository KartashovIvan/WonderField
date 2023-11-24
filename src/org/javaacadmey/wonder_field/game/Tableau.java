package org.javaacadmey.wonder_field.game;

import java.util.Arrays;

public class Tableau {
    //  Условие 2.1
    private String correctAnswer;
    private String[] letters;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    //  Условие 2.2
    public void initTableau(String correct_answer) {
        this.correctAnswer = correct_answer;
        this.letters = new String[correct_answer.length()];
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
    public boolean openLetter(String answer) {
        checkField();

        int[] keyArray = similarCharacters(answer);
        if (keyArray.length > 0) {
            for (int i : keyArray) {
                letters[i] = answer;
            }
            return true;
        }
        return false;
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

    //  Условие 2.5
    public boolean openCorrectAnswer(String answer) {
        if (answer.equalsIgnoreCase(correctAnswer)) {
            letters = correctAnswer.split("");
            return true;
        }
        return false;
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
