package com.example.swen766_bettermaps;

import com.google.android.gms.maps.GoogleMap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private Location origin;
    private Location destination;
    private TravelMode mode;
    private List<Location> stops;

    public Route(Location origin, Location destination, TravelMode mode, Location... stops) {
        this.origin = origin;
        this.destination = destination;
        this.mode = mode;
        this.stops = new LinkedList<>();
        if (stops.length > 0 && !Collections.addAll(this.stops, stops)) {
            throw new RuntimeException("Unable to add stops to route.");
        }
    }

    public Route(Location origin, Location destination, Location... stops) {
        this(origin, destination, TravelMode.WALKING, stops);
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public TravelMode getMode() {
        return mode;
    }

    public void setMode(TravelMode mode) {
        this.mode = mode;
    }

    public List<Location> getStops() {
        return stops;
    }

    public void addStop(Location stop) {
        this.stops.add(stop);
    }

    public void addStop(int index, Location stop) {
        this.stops.add(index, stop);
    }

    public void drawRoute(GoogleMap mMap) {
        MapUtils.drawRoute(mMap,this, BuildConfig.MAPS_API_KEY);
    }
}