package org.javaacadmey.wonder_field.player;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.javaacadmey.wonder_field.game.Game;
import org.javaacadmey.wonder_field.game.Item;

public class Player {
    private final String city;
    private final String name;
    private int gamePoints = 0;
    private int money = 0;
    private int counter = 0;
    private String[] items = new String[0];

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

    public int getCounter() {
        return counter;
    }

    public String getItems() {
        return Arrays.toString(items);
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String sayLetter() {
        String answer = writeLetter();
        System.out.println("Игрок " + this.name + ": буква " + answer);
        return answer;
    }

    public String writeLetter() {
        System.out.println("Скажите букву");
        String answer = Game.readConsole();
        Pattern patternLetter = Pattern.compile("[а-яА-Я]{1}");
        Matcher matcherLetter = patternLetter.matcher(answer);

        while (!matcherLetter.matches() || answer.length() != 1) {
            System.out.println("Нужно сказать только одну букву из русского алфавита");
            answer = Game.readConsole();
            matcherLetter = patternLetter.matcher(answer);
        }
        return answer;
    }

    public String sayWord() {
        System.out.println("Скажите слово");
        return Game.readConsole();
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

    public void addItem(Item item) {
        items = Arrays.copyOf(items, items.length + 1);
        items[items.length - 1] = item.getName();
    }
}
