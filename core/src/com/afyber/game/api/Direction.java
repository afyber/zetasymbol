package com.afyber.game.api;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public static Direction stringToEnum(String string) {
        switch (string) {
            case "LEFT":
                return Direction.LEFT;
            case "RIGHT":
                return Direction.RIGHT;
            case "UP":
                return Direction.UP;
            default:
                return Direction.DOWN;
        }
    }
}
