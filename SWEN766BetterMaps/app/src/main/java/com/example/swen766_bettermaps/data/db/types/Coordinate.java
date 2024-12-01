package com.example.swen766_bettermaps.data.db.types;

/**
 * Represents a map coordinate measuring latitudinal and longitudinal degrees.
 */
public class Coordinate {
    private float latitude;
    private float longitude;

    /**
     * Constructs a Coordinate.
     * @param latitude Latitude, in degrees.
     * @param longitude Longitude, in degrees.
     */
    public Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructs a Coordinate with default location of (0.0, 0.0).
     */
    public Coordinate() {
        this(0.0f, 0.0f);
    }

    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate) {
            Coordinate c = (Coordinate)obj;
            return this.latitude == c.latitude && this.longitude == c.longitude;
        }
        return false;
    }

}
