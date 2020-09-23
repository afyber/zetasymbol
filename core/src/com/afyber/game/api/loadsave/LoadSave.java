package com.afyber.game.api.loadsave;

import com.afyber.game.api.Direction;
import com.afyber.game.api.Overworld;
import com.afyber.game.api.overworld.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

// I'm honestly disgusted by some of the stuff I've had to do here
// like I swear there's a less bloaty way to do some of this
public class LoadSave {

    private LoadSave() {
    }

    // welcome to the land of split() and parseInt()
    public static void load(Overworld world, int levelID, int areaID) {
        TileTextureRegions.loadAreaTiles(areaID);
        DecorTextureRegions.loadAreaDecor(areaID);

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
            else if (line.contains("objects")) {
                state = LoadingState.OBJECT;
                continue;
            }

            String[] args = line.replace("\r", "").split(",");

            if (state == LoadingState.TILES) {
                TileDirection direction = TileDirection.stringToEnum(args[2]);

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
                world.player.facing = Direction.stringToEnum(args[2]);
            }
            else if (state == LoadingState.OBJECT) {
                WorldObjectType objectType = WorldObjectType.stringToEnum(args[0]);
                if (objectType == WorldObjectType.LEVEL_TRANSITION) {
                    for (int i = 0; i < 100; i++) {
                        if (world.transitions[i] == null) {
                            world.transitions[i] = new LevelTransition(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                        }
                    }
                }
                else if (objectType == WorldObjectType.DECOR_OBJECT) {
                    for (int i = 0; i < 1000; i++) {
                        if (world.worldObjects[i] == null) {
                            world.worldObjects[i] = new DecorObject(Integer.parseInt(args[1]), Integer.parseInt(args[2]), DecorTextureRegions.createRegion(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6])), Boolean.parseBoolean(args[7]));
                        }
                    }
                }
            }
        }
    }

    public static void save(Overworld world) {

    }
}
