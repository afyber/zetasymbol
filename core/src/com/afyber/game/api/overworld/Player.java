package com.afyber.game.api.overworld;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.Direction;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends WorldObject {
    public Direction facing;
    boolean walking;
    int isWalkingFrames;

    Texture spriteSheet;
    // list of lists, first list
    // 0: LEFT
    // 1: RIGHT
    // 2: UP
    // 3: DOWN
    // second list
    // 0: standing still
    // 1: walk frame 1
    // 2: walk frame 2
    TextureRegion[][] sprites;

    public Player(int x, int y) {
        super(x, y);
        facing = Direction.DOWN;
        walking = false;
        spriteSheet = new Texture("character spritesheet.png");
        initializeSprites();
    }

    private void initializeSprites() {
        sprites = new TextureRegion[4][3];
        for (int i = 0; i < sprites.length; i++) {
            for (int c = 0; c < sprites[0].length; c++) {
                sprites[i][c] = new TextureRegion(spriteSheet, 16 * i, 16 * c, 16, 16);
            }
        }
        sprite = sprites[0][0];
    }

    public void update(float delta, Overworld world) {
        if (ZetaSymbol.input[0]) {
            worldPos[0] -= 0.5;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 16, 16)) {
                worldPos[0] += 0.5;
            }
            facing = Direction.LEFT;
        }
        if (ZetaSymbol.input[1]) {
            worldPos[0] += 0.5;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 16, 16)) {
                worldPos[0] -= 0.5;
            }
            facing = Direction.RIGHT;
        }
        if (ZetaSymbol.input[2]) {
            worldPos[1] += 0.5;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 16, 16)) {
                worldPos[1] -= 0.5;
            }
            facing = Direction.UP;
        }
        if (ZetaSymbol.input[3]) {
            worldPos[1] -= 0.5;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 16, 16)) {
                worldPos[1] += 0.5;
            }
            facing = Direction.DOWN;
        }

        switch (facing) {
            case LEFT:
                sprite = sprites[0][0];
                break;
            case RIGHT:
                sprite = sprites[1][0];
                break;
            case UP:
                sprite = sprites[2][0];
                break;
            case DOWN:
                sprite = sprites[3][0];
                break;
        }
    }
}
