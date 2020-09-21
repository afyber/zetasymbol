package com.afyber.game.api.overworld;

import com.afyber.game.api.Direction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends WorldObject {
    Direction facing;
    boolean walking;

    Texture me;

    public Player(int x, int y) {
        super(x, y);
        facing = Direction.DOWN;
        walking = false;
        me = new Texture("character still.png");
        sprite = new TextureRegion(me, 0, 0, 16, 16);
    }
}
