package com.afyber.game.api.overworld;

import com.afyber.game.api.Overworld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DecorTextureRegions {

    private DecorTextureRegions() {}

    public static void loadAreaDecor(int areaID) {
        // not every decorset has the same layout, so nothing is defined by default
        switch (areaID) {
            case 0:
                Overworld.decorSet = new Texture("tilesets/castle decor.png");
                break;
        }
    }

    public static TextureRegion createRegion(int x, int y, int width, int height) {
        return new TextureRegion(Overworld.decorSet, x, y, width, height);
    }
}
