package com.afyber.game.api.overworld;

public class LevelTransition extends Collision {

    public int direction;

    public LevelTransition(int x, int y, int width, int height, int direction) {
        super(x, y, width, height);
        this.direction = direction;
    }
}
