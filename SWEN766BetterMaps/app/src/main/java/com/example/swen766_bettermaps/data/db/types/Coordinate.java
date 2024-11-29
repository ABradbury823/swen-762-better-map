package com.example.swen766_bettermaps.data.db.types;

public class Coordinate {
    private float latitude;
    private float longitude;

    Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate() {
        this(0.0f, 0.0f);
    }

    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }
}
