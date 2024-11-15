package com.example.swen766_bettermaps;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private String name;
    private LatLng coordinates;

    public Location(String name, LatLng coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public Location(String name, double x, double y) {
        this(name, new LatLng(x, y));
    }

    public String getName() {
        return name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}