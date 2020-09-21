package com.afyber.game.api.overworld;

import com.badlogic.gdx.graphics.Texture;

// TODO
public class Tile extends WorldObject {

    public Tile(int x, int y, Texture sprite) {
        super(x, y);
        this.sprite = sprite;
    }
}
