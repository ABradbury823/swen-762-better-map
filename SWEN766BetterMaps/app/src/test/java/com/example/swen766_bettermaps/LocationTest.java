package com.example.swen766_bettermaps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.google.android.gms.maps.model.LatLng;

public class LocationTest {

    @Before
    public void setUp() {
        loc1 = new Location("test1", 0, 0);
        loc2 = new Location("test2", 1, 1);
        loc3 = new Location("test3", 2, 2);
    }


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
    private Location loc1;
    private Location loc2;
    private Location loc3;



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

    @Test
    public void testAddStopAtIndex() {
        Route r = new Route(loc1, loc2);
        r.addStop(new Location("testStop1", new LatLng(3, 3)));
        r.addStop(0, loc3);

        Location actualStop = r.getStops().get(0);

        assertEquals(loc3, actualStop);
    }

    @Test
    public void testGetOrigin() {
        Route r = new Route(loc1, loc2);
        assertEquals(loc1, r.getOrigin());
    }

    @Test
    public void testSetOrigin() {
        Route r = new Route(loc1, loc2);
        r.setOrigin(loc3);
        assertEquals(loc3, r.getOrigin());
    }

    @Test
    public void testGetDestination() {
        Route r = new Route(loc1, loc2);
        assertEquals(loc2, r.getDestination());
    }

    @Test
    public void testSetDestination() {
        Route r = new Route(loc1, loc2);
        r.setDestination(loc3);
        assertEquals(loc3, r.getDestination());
    }

    @Test
    public void testGetMode() {
        Route r = new Route(loc1, loc2, TravelMode.WALKING);
        assertEquals(TravelMode.WALKING, r.getMode());
    }

    @Test
    public void testSetMode() {
        Route r = new Route(loc1, loc2, TravelMode.WALKING);
        r.setMode(TravelMode.WALKING);
        assertEquals(TravelMode.WALKING, r.getMode());
    }
    @Test
    public void testGetName() {
        // Test for Location with a name
        Location loc = new Location("testName", "1234 Address Street", 12, 34);
        String expectedName = "testName";
        String actualName = loc.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetNameWithNullAddress() {
        // Test for Location with name and null address
        Location loc = new Location("testName", (String) null, 12, 34);
        String expectedName = "testName";
        String actualName = loc.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetAddress() {
        // Test for Location with an address
        Location loc = new Location("testName", "1234 Address Street", 12, 34);
        String expectedAddress = "1234 Address Street";
        String actualAddress = loc.getAddress();
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testGetAddressWithNullAddress() {
        // Test for Location with null address
        Location loc = new Location("testName", (String) null, 12, 34);
        String actualAddress = loc.getAddress();
        assertNull(actualAddress);  // Since address is null, the result should be null
    }

    @Test
    public void testGetAddressWithCoordinates() {
        // Test for Location with coordinates but no address
        Location loc = new Location("testName", new LatLng(12, 34));
        String actualAddress = loc.getAddress();
        assertNull(actualAddress);  // Should return null since no address was provided
    }


}
