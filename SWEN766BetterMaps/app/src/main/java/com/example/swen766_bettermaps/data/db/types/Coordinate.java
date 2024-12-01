package com.example.swen766_bettermaps.data.db.types;

import androidx.annotation.NonNull;

/**
 * Represents a map coordinate measuring latitudinal and longitudinal degrees.
 */
public class Coordinate {
    private float latitude;
    private float longitude;

    /**
     * Constructs a Coordinate.
     * @param latitude Latitude, in degrees. Clamped between [-90, 90].
     * @param longitude Longitude, in degrees. Clamped between [-180, 180].
     */
    public Coordinate(float latitude, float longitude) {
        this.latitude = clampCoordinate(latitude, -90.0f, 90.0f);
        this.longitude = clampCoordinate(longitude, -180.0f, 180.0f);
    }

    /**
     * Constructs a Coordinate with default location of (0.0, 0.0).
     */
    public Coordinate() {
        this(0.0f, 0.0f);
    }

    public float getLatitude() { return latitude; }

    /**
     * Sets the latitude. Does nothing if the absolute value of the argument is greater than 90.
     * @param latitude The new latitude.
     */
    public void setLatitude(float latitude) {
        if(Math.abs(latitude) > 90.0f) return;
        this.latitude = latitude;
    }

    public float getLongitude() { return longitude; }

    /**
     * Sets the longitude. Does nothing if the absolute value of the argument is greater than 180.
     * @param longitude The new longitude.
     */
    public void setLongitude(float longitude) {
        if(Math.abs(longitude) > 180.0f) return;
        this.longitude = longitude;
    }

    /**
     * Clamps a coordinate within the range [min, max].
     * @param value The value to clamp.
     * @return The value clamped between min and max.
     */
    private float clampCoordinate(float value, float min, float max) {
        if(value < min) return min;
        else if(value > max) return max;
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate) {
            Coordinate c = (Coordinate)obj;
            return this.latitude == c.latitude && this.longitude == c.longitude;
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }

    /**
     * Converts the coordinate into a string form in degrees.
     * Since the coordinates include a cardinal direction (N/E/S/W), degree values
     * are represented by their absolute value. North (N) and East (E) coordinates
     * are positive, while South (S) and West (W) coordinates are negative.
     * @return (<code>latitude</code>°N/S, <code>longitude</code>°E/W)
     */
    @NonNull
    public String toStringDegrees() {
        String latSuffix = latitude >= 0.0f ? "N" : "S";
        float lat = Math.abs(latitude);

        String lonSuffix = longitude >= 0.0f ?  "E" : "W";
        float lon = Math.abs(longitude);

        return "(" +
            lat + "°" + latSuffix + ", " +
            lon + "°" + lonSuffix + ')';
    }
}
