package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.player.PlayerAnswer;

public class Tableau {
    //  Условие 2.1
    private String correctAnswer = "";
    private String[] letters;

    //  Условие 2.2
    public void initTableau(Exercise exercise) {
        this.correctAnswer = exercise.getAnswer().toLowerCase();
        letters = correctAnswer.replaceAll(".", "_").split("");
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
        String answer = playerAnswer.getAnswer();

        for (int i = 0; i < correctAnswer.length(); i++) {
            letters[i] = correctAnswer.charAt(i) == answer.charAt(0) ? answer : letters[i];
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
