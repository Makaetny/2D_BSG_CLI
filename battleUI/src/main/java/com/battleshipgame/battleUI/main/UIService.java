package com.battleshipgame.battleUI.main;


/**
 * Game UI functionality
 *
 * @author Makaetny
 */
public interface UIService {

    /**
     * Handles menu printing and receives user input
     * @return users input
     */
    int startMenu();

    /**
     * Prints board
     * @param board - board to be printed
     * @param boardOwner - boards owner (player of CPU).
     */
    void printBoard(int[][] board, String boardOwner);

    /**
     * Prints turn dialog.
     * @param playerName - players tag
     * @return array of two integer values referring to user input.
     */
    int[] printTurnDialog(String playerName);

    /**
     * Receives messages from the game and prints them to the console
     * @param message
     */
    void sendMessageToUI(String message);

}
