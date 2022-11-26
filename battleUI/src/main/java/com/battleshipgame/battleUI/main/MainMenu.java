package com.battleshipgame.battleUI.main;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public int printMenu() {
        printTitle();
        return inputHandler();
    }

    public int inputHandler() {
        int choice = scanner.nextInt();
        switch(choice) {
            case 1: {
                System.out.println();
                System.out.println("Starting the game");
                System.out.println();
                break;
            }

            case 2: {
                System.out.println();
                System.out.println("Shutting down");
                System.out.println();
                break;
            }

            case 3: {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("Made by me LOL");
                System.out.println();
                System.out.println();
                System.out.println();
                break;
            }
        }
        return choice;
    }

    private void printTitle() {
        System.out.println("  _________     ________   ____________  __        ________");
        System.out.println("  \\  ______ \\   \\   ___ \\  \\__  ___  __\\ \\ \\       \\   ____)");
        System.out.println("   \\ \\_____) \\   \\  \\__\\ \\    \\ \\  \\ \\    \\ \\       \\  \\___    ");
        System.out.println("    \\ \\_____  \\   \\  ____ \\    \\ \\  \\ \\    \\ \\       \\  ___)");
        System.out.println("     \\ \\____)  \\   \\ \\   \\ \\    \\ \\  \\ \\    \\ \\_____  \\ \\_____");
        System.out.println("      \\_________)   \\_\\   \\_\\    \\_\\  \\_\\    \\______)  \\_______)");
        System.out.println();

        System.out.println("    _________    ________   _____ ___   ________");
        System.out.println("    \\   ______\\  \\   ___ \\  \\        \\  \\   ____)");
        System.out.println("     \\  \\  ____   \\  \\__\\ \\  \\   /_)  \\  \\  \\_            PLAY THE GAME [1]");
        System.out.println("      \\  \\  \\__ \\  \\  ____ \\  \\  \\  \\  \\  \\  ___)         SHUT DOWN     [2]");
        System.out.println("       \\  \\____\\ \\  \\ \\   \\ \\  \\  \\  \\  \\  \\ \\______      CREDITS       [3]");
        System.out.println("        \\_________\\  \\_\\   \\_\\  \\__\\  \\__\\  \\_______)");
        System.out.println();
        System.out.println("Version BETA 1.0.0");
    }
}
