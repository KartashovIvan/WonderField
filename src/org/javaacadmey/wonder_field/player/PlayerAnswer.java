package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    private TypeAnswer typeAnswer;
    private String answer;

    public PlayerAnswer(TypeAnswer typeAnswer, String answer) {
        this.typeAnswer = typeAnswer;
        this.answer = answer;
    }

    public TypeAnswer getTypeAnswer() {
        return typeAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}
