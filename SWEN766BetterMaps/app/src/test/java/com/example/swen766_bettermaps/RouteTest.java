package com.example.swen766_bettermaps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.android.gms.maps.model.LatLng;

public class RouteTest {
    private Location loc1;
    private Location loc2;

    @Before
    public void setUp() {
        loc1 = new Location("test1", 0, 0);
        loc2 = new Location("test2", 1, 1);
    }

    @Test
    public void createWithTravelMode() {
        Route r = new Route(
                loc1,
                loc2,
                TravelMode.WALKING
        );

        assertNotNull(r);
    }

    @Test
    public void createWithZeroStops() {
        Route r = new Route(
                loc1,
                loc2
        );
        int expectedStops = 0;

        int actualStops = r.getStops().size();

        assertNotNull(r);
        assertEquals(expectedStops, actualStops);
    }

    @Test
    public void testAddStop() {
        Route r = new Route(loc1, loc2);
        int expectedStops = r.getStops().size() + 1;

        r.addStop(new Location("test", new LatLng(2, 2)));
        int actualStops = r.getStops().size();

        assertEquals(expectedStops, actualStops);
    }
}