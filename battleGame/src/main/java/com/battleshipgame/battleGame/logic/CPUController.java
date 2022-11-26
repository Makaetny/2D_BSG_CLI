package com.battleshipgame.battleGame.logic;

import com.battleshipgame.battleGame.setup.GameInput;

/**
 *
 * Defines interface for AI opponent. Basic input generating functions.
 *
 * @author Makaetny
 *
 */
public interface CPUController {

    /**
     * Calculates input
     * @return GameInput object holding calculated input indexes
     */
    GameInput calculateInput();

    /**
     * Executes after successful hit
     * @param input - GameInput object that holds indexes of the successful hit
     */
    void successHit(GameInput input);

    /**
     * Executes after missed hit
     * @param input - GameInput object that holds indexes of the missed hit
     */
    void missHit(GameInput input);

    /**
     * Executes after successful hit that sinks the ship
     * @param input - GameInput object that holds indexes of the successful hit
     */
    void shipSunken(GameInput input);
}
