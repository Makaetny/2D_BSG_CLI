package com.battleshipgame.battleUI.main;

import java.util.Scanner;

public class MainUI {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ROW = "    +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+ ";
    private static final String SIDE = "  |  ";
    private static final char BLANK = ' ';
    private final char[] validLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final int[][] usedIndexes = new int[10][10];

    public void printBoard(int[][] board, String boardOwner) {
        System.out.println("    [" + boardOwner + "'S " + "BOARD]");
        System.out.println();
        System.out.println("       A     B     C     D     E     F     G     H     I     J");

        for (int i = 0; i < board.length; i++) {
            System.out.print(ROW);
            System.out.println();

            if (i < 9) {
                System.out.print(" ");
            }

            System.out.print(i + 1);

            for (int j = 0; j < board.length; j++) {
                System.out.print(SIDE);
                if (board[i][j] == 0) {
                    System.out.print(BLANK);
                }

                else if (board[i][j] == 2) {
                    System.out.print("o");
                }

                else if (board[i][j] == 1) {
                    System.out.print(" ");
                }

                else if (board[i][j] == -1) {
                    System.out.print("x");
                }
            }
            System.out.println(SIDE);
        }
        System.out.println(ROW);
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
        }
        System.out.flush();
    }

    public int[] printTurnDialog(String playerName) {
        String choiceX;
        String choiceY;
        int[] input = new int[2];
        System.out.println(playerName + "'s turn");

        do {
            System.out.println("Enter Y: (A-J)");
            choiceY = SCANNER.nextLine();

            while (!isInputValidY(choiceY)) {
                System.out.println("Your input: " + choiceY + " is invalid");
                System.out.println("Enter X: (A-J)");
                choiceY = SCANNER.nextLine();
            }
            choiceY = choiceY.toUpperCase();
            input[1] = choiceY.charAt(0) - 65;
            System.out.println("Enter X: (1-10)");
            choiceX = SCANNER.nextLine();

            while (!isInputValidX(choiceX)) {
                System.out.println("Your input: " + choiceX + " is invalid");
                System.out.println("Enter X: (1-10)");
                choiceX = SCANNER.nextLine();
            }
            input[0] = Integer.parseInt(choiceX) - 1;
        } while (isUsedIndex(input));

        usedIndexes[input[0]][input[1]] = 1;
        return input;
    }

    private boolean isUsedIndex(int[] input) {
        if (usedIndexes[input[0]][input[1]] == 1) {
            System.out.println("You have already shot here you dum cunt");
            return true;
        }
        return false;
    }

    private boolean isInputValidY(String y) {
        if (y.length() > 0) {
            if (Character.isLetter(y.charAt(0))) {
                y = y.toUpperCase();
                return isInArray(y.charAt(0));
            }
        }
        return false;
    }
    private boolean isInputValidX(String x) {
        int tmp;
        if (x.length() > 0 && x.length() <= 2) {
            if (Character.isDigit(x.charAt(0))) {
                if (x.length() == 2) {
                    if (Character.isDigit(x.charAt(1))) {
                        tmp = Integer.parseInt(x);
                        return tmp > 0 && tmp < 11;
                    }
                }
                tmp = Integer.parseInt(x);
                return tmp > 0 && tmp < 11;
            }
        }
        return false;
    }

    private boolean isInArray(char input) {
        int index = 0;
        while (index < validLetters.length) {
            if (validLetters[index] == input) {
                return true;
            }
            index++;
        }
        return false;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
