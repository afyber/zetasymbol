package com.afyber.game.api.loadsave;


public enum WorldObjectType {
    LEVEL_TRANSITION;

    public static WorldObjectType stringToEnum(String string) {
        switch (string) {
            case "LEVEL_TRANSITION":
                return LEVEL_TRANSITION;
        }
        return null;
    }
}
