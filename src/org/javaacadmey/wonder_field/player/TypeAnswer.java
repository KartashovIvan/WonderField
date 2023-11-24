package org.javaacadmey.wonder_field.player;

public enum TypeAnswer {
    WORD("Слово"),
    LETTER("Буква");

    private String title;

    TypeAnswer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
