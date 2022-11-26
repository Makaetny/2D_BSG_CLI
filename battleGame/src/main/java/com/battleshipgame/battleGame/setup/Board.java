package com.battleshipgame.battleGame.setup;

import com.battleshipgame.battleGame.ships.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private static final int BOARD_SIZE = 10;
    private static final int DIRECTION_HORIZONTALLY = 0;
    private static final int DIRECTION_VERTICALLY = 1;
    private static final int MAX = 9;
    private static final int MIN = 1;
    private static final Random RANDOM = new Random();
    private final ShipFactory shipFactory = new ShipFactoryImpl();
    private String boardOwner;
    private Piece[][] board;
    private int boards = 0;
    private final List<Ship> ships = new ArrayList<>();

    public Board(String boardOwner) {
       if (boards < 2) {
           initBoard(boardOwner);
       }
    }

    private void initBoard(String boardOwner) {
        this.boardOwner = boardOwner;
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new EmptyPiece();
            }
        }
        initShips();
        placeShips();
        boards++;
    }

    private void initShips() {
        ships.add(shipFactory.makeBattleShip());
        ships.add(shipFactory.makeCarrier());
        ships.add(shipFactory.makeCruiser());
        ships.add(shipFactory.makeDestroyer());
        ships.add(shipFactory.makeSubmarine());
    }

    private void placeShips() {
        int x;
        int y;
        int direction;

        for (Ship ship : ships) {
            direction = getShipDirection();
            do {
                x = RANDOM.nextInt(MAX) + MIN;
                y = RANDOM.nextInt(MAX) + MIN;
            } while (!checkSpaceForShip(direction, ship, x, y));
            setShip(x, y, ship, direction);
        }
    }

    private int getShipDirection() {
        int direction = RANDOM.nextInt(2) + 1;
        if (direction == 1) {
            return DIRECTION_HORIZONTALLY;
        } else {
            return DIRECTION_VERTICALLY;
        }
    }

    private boolean checkSpaceForShip(int direction, Ship ship, int x, int y) {
        if (board[x][y] instanceof EmptyPiece && getDirectionSum(direction, x, y, ship) < 10) {
            return isPlaceAvailable(direction, x, y, ship.getSize());
        } else {
            return false;
        }
    }

    /**
     * calculates the possible index of the end of the ship
     *
     * @param direction
     * @param x value of x
     * @param y value of y
     * @param ship current ship
     * @return index where the end of the ship would be
     */
    private int getDirectionSum(int direction, int x, int y, Ship ship) {
        int result;
        if (direction == DIRECTION_HORIZONTALLY) {
            result = y + ship.getSize();
        }
        else {
            result = x + ship.getSize();
        }
        return result;
    }

    private boolean isPlaceAvailable(int direction, int x, int y, int shipSize) {
        int index = 0;
        while (index <= shipSize) {
            if (board[x][y] instanceof Ship) {
                return false;
            } else {
                index++;
                if (direction == DIRECTION_HORIZONTALLY) {
                    y++;
                } else {
                    x++;
                }
            }
        }
        return true;
    }

    public void setShip(int x, int y, Ship ship, int direction) {
        for (int i = 0; i < ship.getSize(); i++) {
            board[x][y] = ship;
            ship.setCoordinates(x, y);
            if (direction == DIRECTION_HORIZONTALLY) {
                y++;
            } else {
                x++;
            }
        }
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public String getBoardOwner() {
        return boardOwner;
    }

    public boolean isAnyAlive() {
        for (Ship ship : ships) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
