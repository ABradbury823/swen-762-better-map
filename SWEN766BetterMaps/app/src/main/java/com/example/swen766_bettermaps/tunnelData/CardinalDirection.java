package com.example.swen766_bettermaps.tunnelData;

public enum CardinalDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    // Optional: Add a method to get the opposite direction
    public CardinalDirection opposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case EAST:  return WEST;
            case SOUTH: return NORTH;
            case WEST:  return EAST;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
