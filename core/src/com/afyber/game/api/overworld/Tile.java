package com.afyber.game.api.overworld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends WorldObject {

    public Tile(int x, int y, TextureRegion sprite) {
        super(x, y);
        this.sprite = sprite;
    }
}
