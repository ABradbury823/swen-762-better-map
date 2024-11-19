package com.example.swen766_bettermaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.android.gms.maps.model.LatLng;

public class LocationTest {
    @Test
    public void create() {
        Location loc = new Location("test", "1234 Address Street", 12, 34);
        assertNotNull(loc);
    }

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
    public void createWithAddress() {
        Location loc = new Location("test", "1234 Address Street");
        assertNotNull(loc);
    }

    @Test
    public void testGetCoordinates() {
        LatLng expected = new LatLng(0, 0);
        Location loc = new Location("test", 0, 0);
        LatLng actual = loc.getCoordinates();
        assertEquals(expected, actual);
    }

    @Test
    public void testUrlFormatCoordinates() {
        double x = 12;
        double y = 34;
        LatLng coords = new LatLng(x, y);
        Location loc = new Location("test", coords);
        String expected = x + "," + y;
        String actual = loc.urlFormat();
        assertEquals(expected, actual);
    }

    @Test
    public void testUrlFormatAddress() {
        String address = "123 Sesame Street";
        Location loc = new Location("test", address);
        String expected = address.replaceAll(" ", "+");
        String actual = loc.urlFormat();
        assertEquals(expected, actual);
    }

    @Test
    public void testUrlFormatPrioritizeAddress() {
        String address = "123 Sesame Street";
        LatLng coords = new LatLng(12, 34);
        Location loc = new Location("test", address, coords);
        String expected = address.replaceAll(" ", "+");
        String actual = loc.urlFormat();
        assertEquals(expected, actual);
    }
}
