package com.battleshipgame.battleGame.setup;

import com.battleshipgame.battleGame.bean.HitValues;
import com.battleshipgame.battleGame.logic.CPUController;
import com.battleshipgame.battleGame.logic.CPUControllerImpl;
import com.battleshipgame.battleGame.ships.EmptyPiece;
import com.battleshipgame.battleGame.ships.Piece;
import com.battleshipgame.battleGame.ships.Ship;
import com.battleshipgame.battleUI.main.UIService;
import java.io.IOException;

public class Game {

    Board playerBoard;
    Board cpuBoard;
    GameInitializer gameInitializer;
    UIService uiService;
    CPUController cpuController;

    public Game(UIService uiService) {
        this.uiService = uiService;
        gameInitializer = new GameInitializer();
    }

    private void initGame() {
        playerBoard = gameInitializer.makeBoard("PLAYER");
        cpuBoard = gameInitializer.makeBoard("CPU");
        cpuController = new CPUControllerImpl();
    }

    public void startGame() {
        System.out.println("Game starting");
        System.out.println();
        initGame();
        play();
    }

    public void printBoard(Board board) {
        uiService.printBoard(changeBoardToPrintableFormat(board.getBoard()), board.getBoardOwner());
    }

    private int[][] changeBoardToPrintableFormat(Piece[][] board) {
        int[][] printableBoard = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board.length; j++) {

                if (board[i][j] instanceof EmptyPiece) {

                    if (((EmptyPiece) board[i][j]).getCoordinate(i, j) == 1) {
                        printableBoard[i][j] = 2;
                    } else {
                        printableBoard[i][j] = 0;
                    }

                } else if (board[i][j] instanceof Ship) {

                    if (((Ship) board[i][j]).getCoordinate(i, j) == -1) {
                        printableBoard[i][j] = -1;
                    } else if (((Ship) board[i][j]).getCoordinate(i, j) == 1) {
                        printableBoard[i][j] = 1;
                    }
                }
            }
        }
        return printableBoard;
    }

    // The main game loop
    private void play() {
        boolean turn = true;
        while (true) {
            if (turn) {
                playBoardPlayer();
            } else {
                playBoardCPU();
            }

            if (!cpuBoard.isAnyAlive() || !playerBoard.isAnyAlive()) {
                uiService.sendMessageToUI("Game over");
                break;
            }

            turn = turn ? false : true;
        }
    }

    private void playBoardPlayer() {
        GameInput input;
        HitValues result;
        input = cpuController.calculateInput();
        result = playerBoard.getBoard()[input.getX()][input.getY()].hit(input.getX(), input.getY());

        if (result == HitValues.HIT) {
            cpuController.successHit(input);
        } else if (result == HitValues.MISS) {
            cpuController.missHit(input);
        } else if (result == HitValues.SUNKEN) {
            cpuController.shipSunken(input);
        }

        String str = resultHandler(result);
        uiService.sendMessageToUI(str + " X: " + input.getX() + " Y: " + input.getY());
        printBoard((playerBoard));
        turnLatency();
    }

    private void playBoardCPU() {
        printBoard(cpuBoard);
        GameInput input;
        HitValues result;
        int[] playerInput;
        playerInput = uiService.printTurnDialog("PLAYER");
        input = new GameInput(playerInput[0], playerInput[1]);
        result = cpuBoard.getBoard()[input.getX()][input.getY()].hit(input.getX(), input.getY());
        String str = resultHandler(result);
        uiService.sendMessageToUI(str + " X: " + input.getX() + " Y: " + input.getY());
        printBoard((cpuBoard));
        turnLatency();
    }

    private void turnLatency() {
        try {
            Thread.sleep(2400);
        } catch (Exception e) {

        }
    }

    private String resultHandler(HitValues result) {
        String str = "";

        switch (result) {
            case MISS: {
                str = "You missed, fuck!";
                break;
            }

            case HIT: {
                str = "Oh Jesus Christ its a hit!";
                break;
            }

            case SUNKEN: {
                str =  "Hit and now the son of bitch is sinking";
                break;
            }
        }
        return str;
    }
}
