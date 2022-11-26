package com.battleshipgame.battleGame.setup;

public class GameInitializer {

    public Board makeBoard(String owner) {
        return new Board(owner);
    }

}
