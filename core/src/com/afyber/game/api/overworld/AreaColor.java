package com.afyber.game.api.overworld;

public enum AreaColor {
    CASTLE(0.031f, 0.102f, 0.047f);

    float red;
    float green;
    float blue;

    AreaColor(float red, float green, float blue) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public static AreaColor idToEnum(int areaID) {
        switch (areaID) {
            case 0:
                return CASTLE;
            default:
                return null;
        }
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }
}
