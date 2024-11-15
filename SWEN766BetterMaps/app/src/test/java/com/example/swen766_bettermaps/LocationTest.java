package com.example.swen766_bettermaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.android.gms.maps.model.LatLng;

public class LocationTest {
    @Test
    public void createWithCoordinates() {
        Location loc = new Location("test", 0, 0);
        assertNotNull(loc);
    }

    @Test
    public void createWithLatLng() {
        LatLng testLatLng = new LatLng(0, 0);
        Location loc = new Location("test", testLatLng);
        assertNotNull(loc);
    }

    @Test
    public void testGetCoordinates() {
        LatLng expected = new LatLng(0, 0);
        Location loc = new Location("test", 0, 0);
        LatLng actual = loc.getCoordinates();
        assertEquals(expected, actual);
    }
}
