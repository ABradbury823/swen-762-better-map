package com.example.swen766_bettermaps.db.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.swen766_bettermaps.data.db.types.Coordinate;

import org.junit.Test;

public class CoordinateTest {

    /**
     * Tests that the constructor clamps the latitude and longitude between
     * [-90, 90] for latitude and [-180, 180] for longitude.
     */
    @Test
    public void testConstructorClamps() {
        float lat = 501.54f;
        float lon = -195.999f;

        Coordinate coordinate = new Coordinate(lat, lon);

        assertEquals(90.0f, coordinate.getLatitude(), 0.0001f);
        assertEquals(-180.0f, coordinate.getLongitude(), 0.0001f);

        lat = -90.111f;
        lon = 1800.5f;
        coordinate = new Coordinate(lat, lon);

        assertEquals(-90.0f, coordinate.getLatitude(), 0.0001f);
        assertEquals(180.0f, coordinate.getLongitude(), 0.0001f);
    }

    /**
     * Tests that the latitude and longitude setters do not change the
     * values if they are outside of the range [-90, 90] for latitude and [-180, 180] for longitude.
     */
    @Test
    public void testSettersIgnoreOutOfBounds() {
        float latBelow = -908.765f;
        float lonBelow = -1256.64f;
        float latAbove = 111.111f;
        float lonAbove = -180.05f;
        Coordinate coordinate = new Coordinate();

        coordinate.setLatitude(latBelow);
        coordinate.setLongitude(lonBelow);

        assertEquals(0.0f, coordinate.getLatitude(), 0.0001f);
        assertEquals(0.0f, coordinate.getLongitude(), 0.0001f);

        coordinate.setLatitude(latAbove);
        coordinate.setLongitude(lonAbove);

        assertEquals(0.0f, coordinate.getLatitude(), 0.0001f);
        assertEquals(0.0f, coordinate.getLongitude(), 0.0001f);
    }

    /**
     * Tests that two Coordinates are equal if they have the same latitude and longitude.
     */
    @Test
    public void testEqualsTrue() {
        float lat = 5.0f;
        float lon = -10.5f;
        Coordinate coordinate1 = new Coordinate(lat, lon);
        Coordinate coordinate2 = new Coordinate(lat, lon);

        assertEquals(coordinate1, coordinate2);
    }

    /**
     * Tests that two Coordinates are not equal if they have different latitude and longitude.
     */
    @Test
    public void testEqualsFalse() {
        Coordinate coordinate1 = new Coordinate(0.0f, 10.8f);
        Coordinate coordinate2 = new Coordinate(0.0f, 80.8f);

        assertNotEquals(coordinate1, coordinate2);
    }

    /**
     * Tests that toStringDegrees represents coordinates with their absolute value
     * and a matching cardinal direction.
     */
    @Test
    public void testToStringDegrees() {
        float lat = 25.0f;
        float lon = 45.0f;
        Coordinate northEast = new Coordinate(lat, lon);
        Coordinate northWest = new Coordinate(lat, -lon);
        Coordinate southEast = new Coordinate(-lat, lon);
        Coordinate southWest = new Coordinate(-lat, -lon);

        String northEastStr = "(" + lat + "°N" + ", " + lon + "°E" + ")";
        String northWestStr = "(" + lat + "°N" + ", " + lon + "°W" + ")";
        String southEastStr = "(" + lat + "°S" + ", " + lon + "°E" + ")";
        String southWestStr = "(" + lat + "°S" + ", " + lon + "°W" + ")";

        System.out.println(northEast + " | " + northEast.toStringDegrees());
        System.out.println(northWest + " | " + northWest.toStringDegrees());
        System.out.println(southEast + " | " + southEast.toStringDegrees());
        System.out.println(southWest + " | " + southWest.toStringDegrees());

        assertEquals(northEastStr, northEast.toStringDegrees());
        assertEquals(northWestStr, northWest.toStringDegrees());
        assertEquals(southEastStr, southEast.toStringDegrees());
        assertEquals(southWestStr, southWest.toStringDegrees());
    }

}
