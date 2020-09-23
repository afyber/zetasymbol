package com.afyber.game.api.overworld;

import com.afyber.game.api.Overworld;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DecorTextureRegions {

    private DecorTextureRegions() {}

    public static void loadAreaDecor(int areaID) {
        // not every decorset has the same layout, so nothing is defined by default
        Overworld.decorSet = new Texture("tilesets/" + LoadSave.areaIDToString(areaID) + " decor.png");
    }

    public static TextureRegion createRegion(int x, int y, int width, int height) {
        return new TextureRegion(Overworld.decorSet, x, y, width, height);
    }
}
