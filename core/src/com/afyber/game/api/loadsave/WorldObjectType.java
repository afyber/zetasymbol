package com.afyber.game.api.loadsave;


public enum WorldObjectType {
    LEVEL_TRANSITION,
    DECOR_OBJECT;

    public static WorldObjectType stringToEnum(String string) {
        switch (string) {
            case "LEVEL_TRANSITION":
                return LEVEL_TRANSITION;
            case "DECOR_OBJECT":
                return DECOR_OBJECT;
        }
        return null;
    }
}
