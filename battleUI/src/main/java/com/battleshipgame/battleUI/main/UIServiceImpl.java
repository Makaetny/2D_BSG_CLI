package com.battleshipgame.battleUI.main;

public class UIServiceImpl implements UIService {

    private static MainMenu mainMenu = new MainMenu();
    private static MainUI mainUI = new MainUI();

    public int startMenu() {
       int choice = mainMenu.printMenu();
       return choice;
    }

    public void printBoard(int[][] board, String boardOwner) {
        mainUI.printBoard(board, boardOwner);
    }

    public int[] printTurnDialog(String playerName) {
        return mainUI.printTurnDialog(playerName);
    }

    @Override
    public void sendMessageToUI(String message) {
        mainUI.printMessage(message);
    }

}
