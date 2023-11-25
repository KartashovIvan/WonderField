package org.javaacadmey.wonder_field.game;

public class Exercise {

    private final String question;
    private final String answer;

    public Exercise(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
