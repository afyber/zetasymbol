package com.afyber.game.api.overworld;

import com.afyber.game.api.Overworld;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileTextureRegions {

    private TileTextureRegions() {}

    // the tile order is left to right, top to bottom (in the image file)
    // the direction is the direction towards where the player can walk (the north wall stops you from moving down)
    // 0 : NW outer corner
    // 1 : N wall
    // 2 : NE outer corner
    // 3 : W wall
    // 4 : floor
    // 5 : E wall
    // 6 : SW outer corner
    // 7 : S wall
    // 8 : SE outer corner
    // 9 : SE inner corner
    // 10: SW inner corner
    // 11: NE inner corner
    // 12: NW inner corner
    private static TextureRegion[] tiles;

    public static void loadAreaTiles(int areaID) {
        Overworld.tileSet = new Texture("tilesets/" + LoadSave.areaIDToString(areaID) +".png");

        // every tileset has the same layout
        tiles = new TextureRegion[13];
        tiles[0] = new TextureRegion(Overworld.tileSet, 0, 0, 16, 16);
        tiles[1] = new TextureRegion(Overworld.tileSet, 16, 0, 16, 16);
        tiles[2] = new TextureRegion(Overworld.tileSet, 32, 0, 16, 16);
        tiles[3] = new TextureRegion(Overworld.tileSet, 0, 16, 16, 16);
        tiles[4] = new TextureRegion(Overworld.tileSet, 16, 16, 16, 16);
        tiles[5] = new TextureRegion(Overworld.tileSet, 32, 16, 16, 16);
        tiles[6] = new TextureRegion(Overworld.tileSet, 0, 32, 16, 16);
        tiles[7] = new TextureRegion(Overworld.tileSet, 16, 32, 16, 16);
        tiles[8] = new TextureRegion(Overworld.tileSet, 32, 32, 16, 16);
        tiles[9] = new TextureRegion(Overworld.tileSet, 0, 48, 16, 16);
        tiles[10] = new TextureRegion(Overworld.tileSet, 16, 48, 16, 16);
        tiles[11] = new TextureRegion(Overworld.tileSet, 0, 64, 16, 16);
        tiles[12] = new TextureRegion(Overworld.tileSet, 16, 64, 16, 16);
    }

    public static TextureRegion getRegionForDirection(TileDirection direction) {
        switch (direction) {
            case WEST:
                return tiles[3];
            case EAST:
                return tiles[5];
            case NORTH:
                return tiles[1];
            case SOUTH:
                return tiles[7];
            case NW_OUTER:
                return tiles[0];
            case NE_OUTER:
                return tiles[2];
            case SW_OUTER:
                return tiles[6];
            case SE_OUTER:
                return tiles[8];
            case SE_INNER:
                return tiles[9];
            case SW_INNER:
                return tiles[10];
            case NE_INNER:
                return tiles[11];
            case NW_INNER:
                return tiles[12];
            case EMPTY:
                return tiles[4];
        }
        // because I loooooove nullpointerexceptions...
        // that was a joke
        return null;
    }
}
