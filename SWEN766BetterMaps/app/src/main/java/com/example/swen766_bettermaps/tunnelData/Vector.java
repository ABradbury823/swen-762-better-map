package com.example.swen766_bettermaps.tunnelData;

public class Vector {
    public CardinalDirection direction;
    public int magnitude;


    public Vector(CardinalDirection direction, int magnitude) {
        this.direction = direction;
        this.magnitude = magnitude;
    }
    
    public CardinalDirection getDirection() {
        return direction;
    }
    public void setDirection(CardinalDirection direction) {
        this.direction = direction;
    }
    public int getMagnitude() {
        return magnitude;
    }
    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    

    
}
