package com.afyber.game.api.overworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldObject {
    public int[] worldPos;

    public TextureRegion sprite;

    public WorldObject(int x, int y) {
        worldPos = new int[2];
        worldPos[0] = x;
        worldPos[1] = y;
    }

    public void render(SpriteBatch batch, int cameraX, int cameraY) {
        batch.draw(sprite, this.worldPos[0] - cameraX, this.worldPos[1] - cameraY);
    }
}
