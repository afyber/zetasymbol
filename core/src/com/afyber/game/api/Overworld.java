package com.afyber.game.api;

import com.afyber.game.api.overworld.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Overworld {
    // all of the generic tiles from the tileset
    public Tile[] tiles;

    // generic collision (invisible)
    public Collision[] collisions;

    // tells the game to load a new level when you collide with them
    public LevelTransition[] transitions;

    // general objects, can be collidable or not, this could get very confusing
    public WorldObject[] worldObjects;

    // TODO: battle system
    public int[] monsterEncounterIDs;
    public int framesSinceLastEncounter;

    public int currentEncounterID;

    public int levelTransitionDirection;

    public Player player;

    public static Texture tileSet;
    public static Texture decorSet;

    public Overworld() {
        tiles = new Tile[1000];
        collisions = new Collision[1000];
        transitions = new LevelTransition[100];
        worldObjects = new WorldObject[1000];
        monsterEncounterIDs = new int[10];
        framesSinceLastEncounter = 0;
        currentEncounterID = -1;

        player = new Player(0, 0);
    }

    public void render(SpriteBatch batch) {
        for (Tile tile:
             tiles) {
            if (tile != null && tile.sprite != null) {
                tile.render(batch, 0, 0);
            }
        }
        for (Collision collision:
             collisions) {
            if (collision != null) {
                collision.render(batch, 0, 0);
            }
        }
        for (WorldObject object:
             worldObjects) {
            if (object != null) {
                object.render(batch, 0, 0);
            }
        }
        player.render(batch, 0, 0);
    }

    public void update() {
        player.update(this);
        framesSinceLastEncounter += 1;
        if (player.walking && framesSinceLastEncounter >= 420) {
            if (Math.random() * 100 > 99.69) {
                System.out.println("Encounter");
                currentEncounterID = monsterEncounterIDs[(int)(System.currentTimeMillis() % monsterEncounterIDs.length)];
            }
        }
    }

    public boolean collidedWithWorld(float x, float y, float width, float height) {
        for (Collision collision:
             collisions) {
            if (collision != null) {
                if (collision.isColliding(x, y, width, height)) {
                    return true;
                }
            }
        }
        for (WorldObject object:
             worldObjects) {
            if (object != null) {
                // I hate myself for this, like, does this even work?
                // surprisingly it does, but I'm very unhappy with it
                // basically it checks if the object extends Collision
                // then if it does casts it to collision *throws up* to use the isColliding function
                if (Collision.class.isAssignableFrom(object.getClass())) {
                    if (((Collision) object).isColliding(x, y, width, height)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int collidedWithLevelTransition(float x, float y, float width, float height) {
        for (LevelTransition transition:
             transitions) {
            if (transition != null) {
                if (transition.isColliding(x, y, width, height)) {
                    return transition.direction;
                }
            }
        }
        return 0;
    }

    public void dispose() {
        tileSet.dispose();
        decorSet.dispose();
    }

    public void setupMonsterIDs(int areaID) {
        switch (areaID) {
            case 0:
                monsterEncounterIDs = new int[]{0};
                break;
        }
    }
}
