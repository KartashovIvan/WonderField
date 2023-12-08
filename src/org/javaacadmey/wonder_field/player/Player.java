package org.javaacadmey.wonder_field.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javaacadmey.wonder_field.game.Game;

public class Player {
    private final String city;
    private final String name;
    private int gamePoints = 0;
    private int money =0;
    public int counter = 0;

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

    public int getGamePoints() {
        return gamePoints;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String sayLetter() {
        System.out.println("Скажите букву");
        String answer = Game.readConsole();
        Pattern patternLetter = Pattern.compile("[а-яА-Я]{1}");
        Matcher matcherLetter = patternLetter.matcher(answer);

        while (!matcherLetter.matches() || answer.length() != 1) {
            System.out.println("Нужно сказать только одну букву из русского алфавита");
            answer = Game.readConsole();
            matcherLetter = patternLetter.matcher(answer);
        }

        System.out.println("Игрок " + this.name + ": буква " + answer);
        return answer;
    }

    public String sayWord() {
        System.out.println("Скажите слово");
        String answer = Game.readConsole();

        System.out.println("Игрок " + this.name + ": слово " + answer);
        return answer;
    }

    public PlayerAnswer move() {
        System.out.println("Если хотите сказать букву нажмите 'б' и enter, если хотите сказать слово нажмите 'c' и enter");

        while (true) {
            String answer = Game.readConsole();
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

    public void addGamePoints(int gamePoints) {
        this.gamePoints += gamePoints;
    }
}
