package org.javaacadmey.wonder_field.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.javaacadmey.wonder_field.utils.GameLogics.*;

public class Player {
    private String city;
    private String name;

    public Player(String name, String city) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String sayLetter() {
        System.out.println("Скажите букву");
        String answer = readConsole();
        Pattern patternLetter = Pattern.compile("[а-яА-Я]{1}");
        Matcher matcherLetter = patternLetter.matcher(answer);

        while (!matcherLetter.matches() || answer.length() != 1) {
            System.out.println("Нужно сказать только одну букву из русского алфавита");
            answer = readConsole();
            matcherLetter = patternLetter.matcher(answer);
        }

        System.out.println("Игрок " + this.name + ": буква " + answer);
        return answer;
    }

    public String sayWord() {
        System.out.println("Скажите слово");
        String answer = readConsole();

        System.out.println("Игрок " + this.name + ": слово " + answer);
        return answer;
    }

    public PlayerAnswer move() {
        System.out.println("Ход игрока " + this.city + ", " + this.name);
        System.out.println("Если хотите сказать букву нажмите 'б' и enter, если хотите сказать слово нажмите 'c' и enter");

        while (true) {
            String answer = readConsole();
            switch (answer) {
                case ("б"):
                    return new PlayerAnswer(this, TypeAnswer.LETTER, sayLetter());
                case ("с"):
                    return new PlayerAnswer(this, TypeAnswer.WORD, sayWord());
                default:
                    System.out.println("Некорректное значение, введите 'б' или 'с'");
                    break;
            }
        }
    }
}
