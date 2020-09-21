package com.afyber.game.api;

import com.afyber.game.api.overworld.Tile;
import com.afyber.game.api.overworld.TileDirection;
import com.afyber.game.api.overworld.TileTextureRegions;

// TODO
public class LoadSave {
    public static void load(Overworld world, int levelID, int areaID) {
        TileTextureRegions.loadAreaTiles(areaID);
        world.tiles[0] = new Tile(16, 0, TileTextureRegions.getRegionForDirection(TileDirection.NORTH));
        world.tiles[1] = new Tile(32, 0, TileTextureRegions.getRegionForDirection(TileDirection.NORTH));
        world.tiles[2] = new Tile(16, 16, TileTextureRegions.getRegionForDirection(TileDirection.FLOOR));
        world.tiles[3] = new Tile(32, 16, TileTextureRegions.getRegionForDirection(TileDirection.FLOOR));
        world.tiles[4] = new Tile(0, 0, TileTextureRegions.getRegionForDirection(TileDirection.NE_INNER));
        world.tiles[5] = new Tile(0, 16, TileTextureRegions.getRegionForDirection(TileDirection.EAST));

        world.player.worldPos[0] = 100;
        world.player.worldPos[1] = 150;
    }

    public static void save(Overworld world) {

    }
}
