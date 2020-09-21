package com.afyber.game.api;

import com.afyber.game.api.overworld.Tile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// TODO
public class LoadSave {
    public static void load(Overworld world, int levelID) {
        Texture levelSet = new Texture("castle tileset.png");
        world.tiles[0] = new Tile(16, 0, new TextureRegion(levelSet, 16, 0, 16, 16));
        world.tiles[1] = new Tile(32, 0, new TextureRegion(levelSet, 16, 0, 16, 16));
        world.tiles[2] = new Tile(16, 16, new TextureRegion(levelSet, 16, 16, 16, 16));
        world.tiles[3] = new Tile(32, 16, new TextureRegion(levelSet, 16, 16, 16, 16));

        world.player.worldPos[0] = 100;
        world.player.worldPos[1] = 150;
    }

    public static void save(Overworld world) {

    }
}
