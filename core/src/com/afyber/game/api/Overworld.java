package com.afyber.game.api;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.overworld.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Overworld {
    public Tile[] tiles;
    public Collision[] collisions;
    public LevelTransition[] transitions;
    public WorldObject[] worldObjects;

    public int[] monsterEncounterIDs;

    public int levelTransitionDirection;

    public Player player;

    public Overworld() {
        tiles = new Tile[1000];
        collisions = new Collision[1000];
        transitions = new LevelTransition[100];
        monsterEncounterIDs = new int[10];

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
        player.render(batch, 0, 0);
    }

    public void update() {
        player.update(this);
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
}
