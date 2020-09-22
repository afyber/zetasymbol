package com.afyber.game.api;

import com.afyber.game.api.overworld.Collision;
import com.afyber.game.api.overworld.Tile;
import com.afyber.game.api.overworld.TileDirection;
import com.afyber.game.api.overworld.TileTextureRegions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

// I'm honestly disgusted by some of the stuff I've had to do here
// like I swear there's a less bloaty way to do some of this
public class LoadSave {

    // welcome to the land of split() and parseInt()
    public static void load(Overworld world, int levelID, int areaID) {
        TileTextureRegions.loadAreaTiles(areaID);

        FileHandle file = Gdx.files.internal("areas/castle/" + levelID + ".txt");
        String[] fileContents = file.readString().split("\n");

        LoadingState state = LoadingState.TILES;

        for (String line:
             fileContents) {
            if (line.contains("tiles")) {
                state = LoadingState.TILES;
                continue;
            }
            else if (line.contains("collisions")) {
                state = LoadingState.COLLISION;
                continue;
            }
            else if (line.contains("player")) {
                state = LoadingState.PLAYER;
                continue;
            }

            String[] args = line.split(",");

            // probably absolute terribleness
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].replace("\r", "");
            }

            if (state == LoadingState.TILES) {
                TileDirection direction;

                switch (args[2]) {
                    case "WEST":
                        direction = TileDirection.WEST;
                        break;
                    case "EAST":
                        direction = TileDirection.EAST;
                        break;
                    case "NORTH":
                        direction = TileDirection.NORTH;
                        break;
                    case "SOUTH":
                        direction = TileDirection.SOUTH;
                        break;
                    case "NW_OUTER":
                        direction = TileDirection.NW_OUTER;
                        break;
                    case "NE_OUTER":
                        direction = TileDirection.NE_OUTER;
                        break;
                    case "SW_OUTER":
                        direction = TileDirection.SW_OUTER;
                        break;
                    case "SE_OUTER":
                        direction = TileDirection.SE_OUTER;
                        break;
                    case "SE_INNER":
                        direction = TileDirection.SE_INNER;
                        break;
                    case "SW_INNER":
                        direction = TileDirection.SW_INNER;
                        break;
                    case "NE_INNER":
                        direction = TileDirection.NE_INNER;
                        break;
                    case "NW_INNER":
                        direction = TileDirection.NW_INNER;
                        break;
                    default:
                        direction = TileDirection.EMPTY;
                }
                for (int i = 0; i < 1000; i++) {
                    if (world.tiles[i] == null) {
                        world.tiles[i] = new Tile(Integer.parseInt(args[0]), Integer.parseInt(args[1]), TileTextureRegions.getRegionForDirection(direction));
                        break;
                    }
                }
            }
            else if (state == LoadingState.COLLISION) {
                for (int i = 0; i < 1000; i++) {
                    if (world.collisions[i] == null) {
                        world.collisions[i] = new Collision(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        break;
                    }
                }
            }
            else if (state == LoadingState.PLAYER) {
                world.player.worldPos[0] = Integer.parseInt(args[0]);
                world.player.worldPos[1] = Integer.parseInt(args[1]);
                Direction direction;
                switch (args[2]) {
                    case "LEFT":
                        direction = Direction.LEFT;
                        break;
                    case "RIGHT":
                        direction = Direction.RIGHT;
                        break;
                    case "UP":
                        direction = Direction.UP;
                        break;
                    default:
                        direction = Direction.DOWN;
                        break;
                }
                world.player.facing = direction;
            }
        }
    }

    public static void save(Overworld world) {

    }
}
