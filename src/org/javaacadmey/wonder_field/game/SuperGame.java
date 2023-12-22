package org.javaacadmey.wonder_field.game;

import java.util.Arrays;
import java.util.Random;
import org.javaacadmey.wonder_field.domain.Exercise;
import org.javaacadmey.wonder_field.domain.Item;
import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.TypeAnswer;

public class SuperGame {
    private final int numberOfItems = 6;
    private final int numberOfSuperItems = 6;
    private Item[] items = new Item[numberOfItems];
    private final Item[] superItems = new Item[numberOfSuperItems];

    public void initSuperGame(Player winner, Exercise exercise, Yakubovich yakubovich, Tableau tableau) {
        createItems();
        chooseItems(winner);
        createSuperItems();
        Random random = new Random();
        Item item = superItems[random.nextInt(0, numberOfSuperItems - 1)];
        if (yakubovich.askAboutSuperGame(winner)) {
            startSuperGame(winner, exercise, yakubovich, tableau, item);

        } else {
            System.out.println("Жаль, вы бы могли получить " + item.getName());
        }
    }

    private void startSuperGame(Player winner, Exercise exercise, Yakubovich yakubovich, Tableau tableau, Item item) {
        yakubovich.askQuestion(exercise);
        tableau.initTableau(exercise);
        for (String answer : askThreeLetters(winner)) {
            PlayerAnswer playerAnswer = new PlayerAnswer(winner, TypeAnswer.LETTER, answer);
            checkLetter(playerAnswer, exercise, tableau);
        }
        System.out.println("Правильные буквы открыты!");
        tableau.showTableau();
        if (checkWord(winner.sayWord(), exercise, tableau)) {
            winner.addItem(item);
        }
    }

    private void checkLetter(PlayerAnswer playerAnswer, Exercise exercise, Tableau tableau) {
        String[] split = exercise.getAnswer().split("");
        for (String str : split) {
            if (str.toLowerCase().contains(playerAnswer.getAnswer().toLowerCase())) {
                tableau.openLetter(playerAnswer);
            }
        }
    }

    private boolean checkWord(String word, Exercise exercise, Tableau tableau) {
        if (word.equalsIgnoreCase(exercise.getAnswer())) {
            System.out.println(word + "! Абсолютно верно!");
            tableau.openCorrectAnswer(word);
            return true;
        } else {
            System.out.println("Жаль, но слово не верно");
            return false;
        }
    }

    private String[] askThreeLetters(Player winner) {
        System.out.println("Назовите 3 буквы");
        String[] letters = new String[3];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = winner.writeLetter();
        }
        return letters;
    }

    private void createItems() {
        items[0] = new Item(1500, "Телевизор");
        items[1] = new Item(1000, "Утюг");
        items[2] = new Item(2000, "Телефон");
        items[3] = new Item(700, "Пылесос");
        items[4] = new Item(1000, "Фен");
        items[5] = new Item(300, "Набор инструментов");
    }

    private void createSuperItems() {
        superItems[0] = new Item("Автомобиль");
        superItems[1] = new Item("Мотоцикл");
        superItems[2] = new Item("Мопед");
        superItems[3] = new Item("Катер");
        superItems[4] = new Item("Велосипед");
        superItems[5] = new Item("Вертолет");
    }

    private void chooseItems(Player winner) {
        if (enoughGamePoints(winner)) {
            boolean end = true;
            while (end) {
                if (items.length == 0) {
                    System.out.println("Больше нет призов которые можно выбрать");
                    return;
                }
                if (enoughGamePoints(winner)) {
                    System.out.println(winner.getName() + ", у вас " + winner.getGamePoints() + "\nВыбирете призы \n" + Arrays.toString(items) + "\nВведите имя приза");
                    compareCost(Game.readConsole(), winner);
                } else {
                    end = false;
                }
            }
        } else {
            System.out.println("Вам не хатает очков для выбора призов");
        }
    }

    private void compareCost(String answer, Player winner) {
        int index = searchItem(answer);
        if (index > -1) {
            if (items[index].getCost() > winner.getGamePoints()) {
                System.out.println("Вам не хватает на " + items[index].getName());
            } else {
                Item item = deleteItem(index);
                winner.setGamePoints(winner.getGamePoints() - item.getCost());
                winner.addItem(item);
            }
        } else {
            System.out.println("Не правильно ввели имя вещи");
        }
    }

    private int searchItem(String nameItem) {
        for (int i = 0; i < items.length; i++) {
            if (nameItem.equalsIgnoreCase(items[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    private Item deleteItem(int index) {
        Item item = items[index];
        for (int i = index + 1; i < items.length; i++) {
            items[i - 1] = items[i];
        }
        items = Arrays.copyOf(items, items.length - 1);
        return item;
    }

    private boolean enoughGamePoints(Player player) {
        int minCost = items[0].getCost();
        for (int i = 1; i < items.length; i++) {
            if (items[i].getCost() < minCost) {
                minCost = items[i].getCost();
            }
        }
        return player.getGamePoints() >= minCost;
    }
}
