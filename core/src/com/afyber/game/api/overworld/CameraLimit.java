package com.afyber.game.api.overworld;

import com.afyber.game.api.Direction;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO
public class CameraLimit extends WorldObject {
    // This is a method to contain the camera to a certain area
    // The direction LEFT would mean the x value is as far left as the left edge of the camera can go
    public Direction type;

    public CameraLimit(int x, int y, Direction type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public void render(SpriteBatch batch, float cameraX, float cameraY) {
        // none, should be invisible
    }
}
