package com.afyber.game.api.overworld;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.Direction;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends WorldObject {
    public Direction facing;
    public boolean walking;
    private int isWalkingFrames;

    Texture spriteSheet;
    // list of lists, first list
    // 0: LEFT
    // 1: RIGHT
    // 2: UP
    // 3: DOWN
    // second list
    // 0: standing still
    // 1: walk frame 1
    // 2: standing still again
    // 3: walk frame 2
    TextureRegion[][] sprites;

    public Player(int x, int y) {
        super(x, y);
        facing = Direction.DOWN;
        walking = false;
        spriteSheet = new Texture("character spritesheet.png");
        initializeSprites();
    }

    private void initializeSprites() {
        sprites = new TextureRegion[4][4];
        for (int i = 0; i < sprites.length; i++) {
            for (int c = 0; c < sprites[0].length; c++) {
                sprites[i][c] = new TextureRegion(spriteSheet, 10 * i, 11 * c, 10, 11);
            }
        }
        sprite = sprites[0][0];
    }

    public void update(Overworld world) {
        if (ZetaSymbol.input.isKeyDown(0)) {
            worldPos[0] -= 0.75;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 10, 11)) {
                worldPos[0] += 0.75;
            }
            facing = Direction.LEFT;
        }
        if (ZetaSymbol.input.isKeyDown(1)) {
            worldPos[0] += 0.75;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 10, 11)) {
                worldPos[0] -= 0.75;
            }
            facing = Direction.RIGHT;
        }
        if (ZetaSymbol.input.isKeyDown(2)) {
            worldPos[1] += 0.75;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 10, 11)) {
                worldPos[1] -= 0.75;
            }
            facing = Direction.UP;
        }
        if (ZetaSymbol.input.isKeyDown(3)) {
            worldPos[1] -= 0.75;
            if (world.collidedWithWorld(worldPos[0], worldPos[1], 10, 11)) {
                worldPos[1] += 0.75;
            }
            facing = Direction.DOWN;
        }

        // this whole thing is dumb
        if (ZetaSymbol.input.isKeyDown(0) || ZetaSymbol.input.isKeyDown(1) ||
                ZetaSymbol.input.isKeyDown(2) || ZetaSymbol.input.isKeyDown(3)) {
            isWalkingFrames += 1;
            if (isWalkingFrames > 59) {
                isWalkingFrames = 0;
            }
            walking = true;
        }
        else {
            isWalkingFrames = 0;
            walking = false;
        }

        switch (facing) {
            case LEFT:
                sprite = sprites[0][isWalkingFrames / 15];
                break;
            case RIGHT:
                sprite = sprites[1][isWalkingFrames / 15];
                break;
            case UP:
                sprite = sprites[2][isWalkingFrames / 15];
                break;
            case DOWN:
                sprite = sprites[3][isWalkingFrames / 15];
                break;
        }

        int result = world.collidedWithLevelTransition(worldPos[0], worldPos[1], 10, 11);
        if (result != 0) {
            world.levelTransitionDirection = result;
        }
    }
}
