package com.example.swen766_bettermaps;

import com.google.android.gms.maps.model.LatLng;

public class Location {
    private String name;
    private String address;
    private LatLng coordinates;

    public Location(String name, String address, LatLng coordinates) {
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
    }

    public Location(String name, String address) {
        this(name, address, null);
    }

    public Location(String name, LatLng coordinates) {
        this(name, null, coordinates);
    }

    public Location(String name, double x, double y) {
        this(name, new LatLng(x, y));
    }

    public Location(String name, String address, double x, double y) {
        this(name, address, new LatLng(x, y));
    }

    public String getName() {
        return name;
    }

    public String getAddress() { return address; }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public String urlFormat() {
        if (address != null) {
            return address.replaceAll(" ", "+");
        } else {
            return coordinates.latitude + "," + coordinates.longitude;
        }
    }
}