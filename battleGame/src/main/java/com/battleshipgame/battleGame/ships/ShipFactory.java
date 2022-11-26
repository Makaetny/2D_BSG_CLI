package com.battleshipgame.battleGame.ships;

/**
 * Interface for class that makes all defined ship objects
 *
 * @author Makaetny
 */
public interface ShipFactory {

     /**
      * Initiates BattleShip object.
      * @return
      */
     Ship makeBattleShip();

     /**
      * Initiates Carrier object.
      * @return
      */
     Ship makeCarrier();

     /**
      * Initiates Cruiser object.
      * @return
      */
     Ship makeCruiser();

     /**
      * Initiates Destroyer object.
      * @return
      */
     Ship makeDestroyer();

     /**
      * Initiates Submarine object.
      * @return
      */
     Ship makeSubmarine();

}
