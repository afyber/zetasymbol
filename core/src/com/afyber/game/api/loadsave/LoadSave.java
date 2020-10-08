package com.afyber.game.api.loadsave;

import com.afyber.game.api.Direction;
import com.afyber.game.api.Overworld;
import com.afyber.game.api.battle.Rythm;
import com.afyber.game.api.overworld.*;
import com.afyber.game.screens.BattleScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

// I'm honestly disgusted by some of the stuff I've had to do here
// like I swear there's a less bloaty way to do some of this
// The format for levels will be described in docs/LEVELS.txt
public class LoadSave {

    private LoadSave() {
    }

    // welcome to the land of split() and parseInt()
    public static void load(Overworld world, int levelID, int areaID) {
        TileTextureRegions.loadAreaTiles(areaID);
        DecorTextureRegions.loadAreaDecor(areaID);

        world.setupMonsterIDs(areaID);

        FileHandle file = Gdx.files.internal("areas/" + areaIDToString(areaID) + "/" + levelID + ".txt");
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

            // just get rid of whitespace
            String[] args = line.replaceAll("[ \r]", "").split(",");

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

    public static void loadMusic(Rythm current, int musicID, int areaID) {
        FileHandle data = Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + musicID + ".mus");
        String[] textData = data.readString().split("\n");
        for (String line:
                textData) {
            if (!line.startsWith("|")) {
                String[] args = line.replaceAll("[ \r]", "").split(",");
                current.beatsPerSec = Integer.parseInt(args[0]);
                current.secsPerBeat = 60f / current.beatsPerSec;
                current.timeSigTop = Integer.parseInt(args[1]);
                current.usesEighths = Boolean.getBoolean(args[2]);
                current.noteData = new int[100][];
            }
            else {
                String[] args = line.replace("|", "").split(" ");
                for (int i = 0; i < current.noteData.length; i++) {
                    if (current.noteData[i] == null) {
                        if (current.usesEighths) {
                            current.noteData[i] = new int[current.timeSigTop * 2];
                            for (int c = 0; c < current.timeSigTop * 2; c++) {
                                current.noteData[i][c] = Integer.parseInt(args[i]);
                            }
                        }
                        else {
                            current.noteData[i] = new int[current.timeSigTop];
                            for (int c = 0; c < current.timeSigTop; c++) {
                                current.noteData[i][c] = Integer.parseInt(args[c]);
                            }
                        }
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < current.noteData.length; i++) {
            if (current.noteData[i] == null) {
                if (current.usesEighths) {
                    current.noteData[i] = new int[current.timeSigTop * 2];
                }
                else {
                    current.noteData[i] = new int[current.timeSigTop];
                }
            }
        }
        current.ratings = new ArrayList<>();
    }

    public static void loadMonster(BattleScreen screen, int monsterID, int areaID) {
        FileHandle data = Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + monsterID + ".mon");
        String[] textData = data.readString().split("\n");
        String[] args = textData[0].replaceAll("[\r ]", "").split(",");
        screen.monsterHealth = Integer.parseInt(args[0]);
        screen.monsterDef = Integer.parseInt(args[1]);
        screen.monsterMaxHealth = Integer.parseInt(args[2]);
    }

    public static void save(Overworld world) {

    }

    public static String areaIDToString(int areaID) {
        switch (areaID) {
            case 0:
                return "castle";
            case 1:
                return "forest";
            case 2:
                return "field";
            case 3:
                return "knowhere";
            case 4:
                return "town";
            default:
                return null;
        }
    }
}
