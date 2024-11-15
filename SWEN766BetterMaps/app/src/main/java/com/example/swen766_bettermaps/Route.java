package com.example.swen766_bettermaps;

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
        if (stops != null && !Collections.addAll(this.stops, stops)) {
            throw new RuntimeException("Unable to add stops to route.");
        }
    }

    public Route(Location origin, Location destination, Location... stops) {
        this(origin, destination, TravelMode.WALKING, stops);
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
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

    public void addStop(Location stop, int index) {
        this.stops.add(index, stop);
    }
}