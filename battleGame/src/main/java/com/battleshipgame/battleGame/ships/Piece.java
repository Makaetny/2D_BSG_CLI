package com.battleshipgame.battleGame.ships;

import com.battleshipgame.battleGame.bean.HitValues;

/**
 * Interface for game board pieces
 *
 * @author Makaetny
 */
public interface Piece {

    /**
     * Hits the ship. Increases hit count and checks if ship is still alive after the hit.
     * @param x - index of the hit
     * @param y - index of the hit
     * @return HitValues enumeration telling the type of hit
     */
    HitValues hit(int x , int y);
}
