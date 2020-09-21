package com.afyber.game.api;

import com.afyber.game.ZetaSymbol;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandling implements InputProcessor {

    public int[] keyCodes;

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
    }

    @Override
    public boolean keyDown(int keycode) {
        for (int keys = 0; keys < keyCodes.length; keys++) {
            if (keycode == keyCodes[keys]) {
                ZetaSymbol.input[keys] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (int keys = 0; keys < keyCodes.length; keys++) {
            if (keycode == keyCodes[keys]) {
                ZetaSymbol.input[keys] = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
