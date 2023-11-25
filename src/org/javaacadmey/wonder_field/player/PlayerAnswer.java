package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    private Player player;
    private TypeAnswer typeAnswer;
    private String answer;

    public PlayerAnswer(Player player, TypeAnswer typeAnswer, String answer) {
        this.player = player;
        this.typeAnswer = typeAnswer;
        this.answer = answer;
    }

    public Player getPlayer() {
        return player;
    }

    public TypeAnswer getTypeAnswer() {
        return typeAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}
