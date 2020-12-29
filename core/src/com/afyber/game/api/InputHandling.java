package com.afyber.game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandling {

    public int[] keyCodes;
    // each index corresponds to a button
    // the content determines the state
    // 0: Left
    // 1: Right
    // 2: Up
    // 3: Down
    // 4: A
    // 5: B
    // 6: Pause/Start
    public int[] inputState;

    public InputHandling() {
        // TODO: configurable input
        keyCodes = new int[7];
        keyCodes[0] = Input.Keys.LEFT;
        keyCodes[1] = Input.Keys.RIGHT;
        keyCodes[2] = Input.Keys.UP;
        keyCodes[3] = Input.Keys.DOWN;
        keyCodes[4] = Input.Keys.Z;
        keyCodes[5] = Input.Keys.X;
        keyCodes[6] = Input.Keys.C;
        inputState = new int[7];
    }

    // IMPORTANT: This must be called by every screen that needs input!
    public void update() {
        for (int num = 0; num < keyCodes.length; num++) {
            if (Gdx.input.isKeyJustPressed(keyCodes[num])) {
                inputState[num] = 1;
            } else if (Gdx.input.isKeyPressed(keyCodes[num])) {
                inputState[num] = 2;
            } else {
                inputState[num] = 0;
            }
        }
    }

    public boolean isKeyDown(int keyCode) { return inputState[keyCode] > 0; }

    public boolean keyJustPressed(int keyCode) { return inputState[keyCode] == 1; }
}
