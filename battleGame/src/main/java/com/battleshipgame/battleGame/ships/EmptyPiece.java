package com.battleshipgame.battleGame.ships;

import com.battleshipgame.battleGame.bean.HitValues;

public class EmptyPiece implements Piece {

    private final int[][] coordinates = new int[10][10];

    public HitValues hit(int x, int y) {
        coordinates[x][y] = 1;
        return HitValues.MISS;
    }

    public int getCoordinate(int x, int y) {
        return coordinates[x][y];
    }
}
