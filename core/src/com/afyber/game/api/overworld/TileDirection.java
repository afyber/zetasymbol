package com.afyber.game.api.overworld;

public enum TileDirection {
    WEST,
    EAST,
    NORTH,
    SOUTH,
    NW_OUTER,
    NE_OUTER,
    SW_OUTER,
    SE_OUTER,
    SE_INNER,
    SW_INNER,
    NE_INNER,
    NW_INNER,
    EMPTY;

    public static TileDirection stringToEnum(String string) {
        switch (string.toUpperCase()) {
            case "WEST":
                return TileDirection.WEST;
            case "EAST":
                return TileDirection.EAST;
            case "NORTH":
                return TileDirection.NORTH;
            case "SOUTH":
                return TileDirection.SOUTH;
            case "NW_OUTER":
                return TileDirection.NW_OUTER;
            case "NE_OUTER":
                return TileDirection.NE_OUTER;
            case "SW_OUTER":
                return TileDirection.SW_OUTER;
            case "SE_OUTER":
                return TileDirection.SE_OUTER;
            case "SE_INNER":
                return TileDirection.SE_INNER;
            case "SW_INNER":
                return TileDirection.SW_INNER;
            case "NE_INNER":
                return TileDirection.NE_INNER;
            case "NW_INNER":
                return TileDirection.NW_INNER;
            default:
                return TileDirection.EMPTY;
        }
    }
}
