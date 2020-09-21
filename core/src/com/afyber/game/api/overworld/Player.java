package com.afyber.game.api.overworld;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.Direction;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends WorldObject {
    Direction facing;
    boolean walking;

    Texture me;

    public Player(int x, int y) {
        super(x, y);
        facing = Direction.DOWN;
        walking = false;
        me = new Texture("character still.png");
        sprite = new TextureRegion(me, 0, 0, 16, 16);
    }

    public void update(float delta, Overworld world) {
        if (ZetaSymbol.input[0]) {
            worldPos[0] -= 0.5;
            if (world.collidedWithWorld((int)(worldPos[0]), (int)(worldPos[1]), 16, 16)) {
                worldPos[0] += 0.5;
            }
        }
        if (ZetaSymbol.input[1]) {
            worldPos[0] += 0.5;
            if (world.collidedWithWorld((int)(worldPos[0]), (int)(worldPos[1]), 16, 16)) {
                worldPos[0] -= 0.5;
            }
        }
        if (ZetaSymbol.input[2]) {
            worldPos[1] += 0.5;
            if (world.collidedWithWorld((int)(worldPos[0]), (int)(worldPos[1]), 16, 16)) {
                worldPos[1] -= 0.5;
            }
        }
        if (ZetaSymbol.input[3]) {
            worldPos[1] -= 0.5;
            if (world.collidedWithWorld((int)(worldPos[0]), (int)(worldPos[1]), 16, 16)) {
                worldPos[1] += 0.5;
            }
        }
    }
}
