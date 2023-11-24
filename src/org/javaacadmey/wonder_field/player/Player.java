package org.javaacadmey.wonder_field.player;

import org.javaacadmey.wonder_field.Game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private String city;
    private String name;

    public Player(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String sayLetter() {
        System.out.println("Скажите букву");
        String answer = Game.READER.nextLine();
        Pattern patternLetter = Pattern.compile("[а-яА-Я]{1}");
        Matcher matcherLetter = patternLetter.matcher(answer);

        while (!matcherLetter.matches() || answer.length() != 1) {
            System.out.println("Нужно сказать только одну букву из русского алфавита");
            answer = Game.READER.nextLine();
            matcherLetter = patternLetter.matcher(answer);
        }

        System.out.println("Игрок " + this.name + ": буква " + answer);
        return answer;
    }

    public String sayWord() {
        System.out.println("Скажите слово");
        String answer = Game.READER.nextLine();

        System.out.println("Игрок " + this.name + ": слово " + answer);
        return answer;
    }

    public PlayerAnswer move() {
        System.out.println("Ход игрока " + this.city + ", " + this.name);
        System.out.println("Если хотите сказать букву нажмите 'б' и enter, если хотите сказать слово нажмите 'c' и enter");

        while (true) {
            String answer = Game.READER.nextLine();
            switch (answer) {
                case ("б"):
                    return new PlayerAnswer(TypeAnswer.LETTER, sayLetter());
                case ("с"):
                    return new PlayerAnswer(TypeAnswer.WORD, sayWord());
                default:
                    System.out.println("Некорректное значение, введите 'б' или 'с'");
                    break;
            }
        }
    }
}
