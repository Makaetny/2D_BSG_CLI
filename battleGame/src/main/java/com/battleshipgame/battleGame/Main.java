package com.battleshipgame.battleGame;

import com.battleshipgame.battleGame.setup.Game;
import com.battleshipgame.battleUI.main.UIService;
import com.battleshipgame.battleUI.main.UIServiceImpl;

public class Main {

    private static boolean gameIsOn = true;
    private static UIService uiService = new UIServiceImpl();
    private static Game game = new Game(uiService);

    public static void main (String[] args) {

        while(gameIsOn) {
            int choice = uiService.startMenu();
            switch (choice) {
                case 1: {
                    game.startGame();
                    break;
                }

                case 2: {
                    gameIsOn = false;
                    break;
                }

                case 3: {
                    break;
                }
            }
        }
    }
}
