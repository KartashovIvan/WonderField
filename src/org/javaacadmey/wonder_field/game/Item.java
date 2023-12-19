package org.javaacadmey.wonder_field.game;

public class Item {
    private int cost;
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return cost + " - " + name;
    }
}
