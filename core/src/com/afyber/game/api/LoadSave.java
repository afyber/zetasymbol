package com.afyber.game.api;

import com.afyber.game.api.overworld.Tile;
import com.badlogic.gdx.graphics.Texture;

// TODO
public class LoadSave {
    public static void load(Overworld world, int levelID) {
        world.tiles[0] = new Tile(100, 100, new Texture("character still.png"));
    }

    public static void save(Overworld world) {

    }
}
