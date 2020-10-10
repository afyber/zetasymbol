package com.afyber.game.api.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUI {

    private Texture square;

    public GUI() {
        square = new Texture(Gdx.files.internal("square.png"));
    }

    /**
     * draws a rectangle's border
     * @param x1 the upper left corner's x coordinate
     * @param x2 the lower right corner's x coordinate
     * @param y1 the upper left corner's y coordinate
     * @param y2 the lower right corner's y coordinate
     * @param batch the batch to draw on
     */
    public void drawRect(float x1, float x2, float y1, float y2, SpriteBatch batch) {
        // x is right
        for (float x = x2; x >= x1; x--) {
            batch.draw(square, x, y1);
            batch.draw(square, x, y2);
        }
        // y is up
        for (float y = y1; y >= y2; y--) {
            batch.draw(square, x1, y);
            batch.draw(square, x2, y);
        }
    }
}
