package com.battleshipgame.battleGame.ships;

public class ShipFactoryImpl implements ShipFactory {


    @Override
    public Ship makeBattleShip() {
        return new Battleship();
    }

    @Override
    public Ship makeCarrier() {
        return new Carrier();
    }

    @Override
    public Ship makeCruiser() {
        return new Cruiser();
    }

    @Override
    public Ship makeDestroyer() {
        return new Destroyer();
    }

    @Override
    public Ship makeSubmarine() {
        return new Submarine();
    }
}
