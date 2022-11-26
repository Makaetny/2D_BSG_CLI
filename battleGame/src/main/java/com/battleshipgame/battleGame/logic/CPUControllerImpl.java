package com.battleshipgame.battleGame.logic;

import com.battleshipgame.battleGame.bean.Direction;
import com.battleshipgame.battleGame.setup.GameInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CPUControllerImpl implements CPUController {

    private static final Random RANDOM = new Random();
    private static final int MAX = 10;
    private static final int MIN = 0;
    private static int[][] usedIndexes;
    private List<GameInput> inputList = new ArrayList<>();
    private boolean findingShip;
    private Direction direction;

    public CPUControllerImpl() {
        init();
    }

    private void init() {
        usedIndexes = new int[10][10];
        inputList.clear();
        direction = Direction.RIGHT;
    }

    public GameInput calculateInput() {
        GameInput input;
        if (findingShip) {
            input = findShip();
        } else {
            input = randomInput();
        }
        return input;
    }

    private GameInput findShip() {
        int x;
        int y;
        boolean check = true;
        int z;

        do {
            x = inputList.get(inputList.size()-1).getX();
            y = inputList.get(inputList.size()-1).getY();
            switch (direction) {
                case RIGHT: {
                    z = y + 1;
                    if (isValid(z)) {
                        if (!isUsed(x, z)) {
                            check = false;
                            y = z;
                        }
                    }
                    break;
                }
                case LEFT: {
                    z = y - 1;
                    if (isValid(z)) {
                        if (!isUsed(x, z)) {
                            check = false;
                            y = z;
                        }
                    }
                    break;
                }
                case UP: {
                    z = x - 1;
                    if (isValid(z)) {
                        if (!isUsed(z, y)) {
                            check = false;
                            x = z;
                        }
                    }
                    break;
                }
                case DOWN: {
                    z = x + 1;
                    if (isValid(z)) {
                        if (!isUsed(z, y)) {
                            check = false;
                            x = z;
                        } else {
                            findingShip = false;
                            check = false;
                        }
                    }
                    break;
                }
            }
        } while(check);
        return new GameInput(x, y);
    }

    private boolean isUsed(int x, int y) {
        if (usedIndexes[x][y] == 1) {
            GameInput tmp;
            if (!inputList.isEmpty()) {
                tmp = inputList.get(0);
                inputList.clear();
                inputList.add(tmp);
                changeDirection();
            }
            return true;
        }
        return false;
    }

    private boolean isValid(int index) {
        if (index >= 0 && index <= 9) {
            return true;
        }
        changeDirection();
        return false;
    }

    public void successHit(GameInput input) {
        findingShip = true;
        inputList.add(input);
        addUsedIndex(input);
    }

    public void missHit(GameInput input) {
        GameInput tmp;
        addUsedIndex(input);
        if (!inputList.isEmpty()) {
            tmp = inputList.get(0);
            inputList.clear();
            inputList.add(tmp);
            if (findingShip) {
                changeDirection();
            }
        }
    }

    public void shipSunken(GameInput input) {
        addUsedIndex(input);
        findingShip = false;
        direction = Direction.RIGHT;
        inputList.clear();
    }

    private void changeDirection() {
        switch (direction) {
            case RIGHT: {
                direction = Direction.LEFT;
                break;
            }
            case LEFT: {
                direction = Direction.UP;
                break;
            }
            case UP: {
                direction = Direction.DOWN;
                break;
            }
        }
    }

    private GameInput randomInput() {
        int[] input = new int[2];
        do {
            input[0] = RANDOM.nextInt(MAX) + MIN;
            input[1] = RANDOM.nextInt(MAX) + MIN;
        } while(usedIndexes[input[0]][input[1]] == 1);

        return new GameInput(input[0], input[1]);
    }

    private void addUsedIndex(GameInput input) {
        usedIndexes[input.getX()][input.getY()] = 1;
    }

}
