package com.battleshipgame.battleGame.ships;

import com.battleshipgame.battleGame.bean.HitValues;

public abstract  class Ship implements Piece{

    protected int size;
    protected boolean isAlive;
    protected int hits;
    protected String name;
    protected char id;
    protected int[][] coordinates = new int[10][10];

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(int x, int y) {
       coordinates[x][y] = 1;
    }

    public int getCoordinate(int x, int y) {
        return coordinates[x][y];
    }

    public HitValues hit(int x, int y) {
        hits++;
        coordinates[x][y] = -1;
        if (isAlive()) {
            return HitValues.HIT;
        }
        else {
            return HitValues.SUNKEN;
        }
    }

    public boolean isAlive() {
        if (hits >= size) {
            return false;
        }
        return true;
    }
}
