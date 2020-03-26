package me.wsman217.healthblocker.utils;

public enum Directions {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NONE;

    public static Directions fromString(String direction) {
        if (direction.equalsIgnoreCase("north"))
            return NORTH;
        else if (direction.equalsIgnoreCase("east"))
            return EAST;
        else if (direction.equalsIgnoreCase("south"))
            return SOUTH;
        else if (direction.equalsIgnoreCase("west"))
            return WEST;
        else
            return NONE;
    }
}
