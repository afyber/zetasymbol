package com.afyber.game.api.overworld;

import com.afyber.game.api.Direction;
import com.badlogic.gdx.graphics.Texture;

public class Player extends WorldObject {
    Direction facing;
    boolean walking;

    public Player(int x, int y) {
        super(x, y);
        facing = Direction.DOWN;
        walking = false;
        sprite = new Texture("character still.png");
    }
}
