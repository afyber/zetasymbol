package com.afyber.game.api;

import com.afyber.game.api.overworld.Collision;
import com.afyber.game.api.overworld.Player;
import com.afyber.game.api.overworld.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Overworld {
    public Tile[] tiles;
    public Collision[] collisions;

    public int[] monsterEncounterIDs;

    public Player player;

    public Overworld() {
        tiles = new Tile[1000];
        collisions = new Collision[1000];
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

    public void update(float delta) {
        player.update(delta, this);
    }

    public boolean collidedWithWorld(int x, int y, int width, int height) {
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
}
