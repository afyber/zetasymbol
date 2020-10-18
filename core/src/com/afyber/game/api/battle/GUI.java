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
     * @param batch the batch to draw on
     * @param x1 the upper left corner's x coordinate
     * @param x2 the lower right corner's x coordinate
     * @param y1 the upper left corner's y coordinate
     * @param y2 the lower right corner's y coordinate
     */
    public void drawRect(SpriteBatch batch, float x1, float x2, float y1, float y2) {
        drawRect(batch, x1, x2, y1, y2, 3);
    }

    /**
     * draws a rectangle's border
     * @param batch the batch to draw on
     * @param x1 the upper left corner's x coordinate
     * @param x2 the lower right corner's x coordinate
     * @param y1 the upper left corner's y coordinate
     * @param y2 the lower right corner's y coordinate
     * @param outlineThickness The scale of the outline
     */
    public void drawRect(SpriteBatch batch, float x1, float x2, float y1, float y2, int outlineThickness) {
        // x is right
        for (float x = x2; x >= x1; x--) {
            batch.draw(square, x, y1, outlineThickness, outlineThickness);
            batch.draw(square, x, y2, outlineThickness, outlineThickness);
        }
        // y is up
        for (float y = y1; y >= y2; y--) {
            batch.draw(square, x1, y, outlineThickness, outlineThickness);
            batch.draw(square, x2, y, outlineThickness, outlineThickness);
        }
    }
}
