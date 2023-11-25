package org.javaacadmey.wonder_field.utils;

import org.javaacadmey.wonder_field.player.Player;

import java.util.Scanner;

public class GameLogics {

    public static final Scanner READER = new Scanner(System.in);

    public static String readConsole(){
        return READER.nextLine();
    }

    //    Условие 5.3
//    public static String[] takeNamePlayers(Player[] players) {
//        int size = players.length;
//        String[] name = new String[size];
//        for (int i = 0; i < size; i++) {
//            name[i] = players[i].getName();
//        }
//        return name;
//    }
}
