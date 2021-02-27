package com.afyber.game.api.overworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldObject {
    public float[] worldPos;

    public TextureRegion sprite;

    public WorldObject(int x, int y) {
        worldPos = new float[2];
        worldPos[0] = x;
        worldPos[1] = y;
    }

    public void render(SpriteBatch batch, float cameraX, float cameraY) {
        batch.draw(sprite, this.worldPos[0] - cameraX, this.worldPos[1] - cameraY);
    }
}
