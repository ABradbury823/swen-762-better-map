package com.example.swen766_bettermaps.db.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.swen766_bettermaps.data.db.types.Coordinate;
import com.example.swen766_bettermaps.data.db.types.CoordinateConverter;

import org.junit.Test;

public class CoordinateConverterTest {

    /**
     * Tests that fromCoordinate converts a Coordinate to a String.
     */
    @Test
    public void testFromCoordinate() {
        float lat = 71.0f;
        float lon = 45.3f;
        Coordinate coordinate = new Coordinate(lat, lon);
        String expectedStr = lat + "," + lon;

        String actualStr = CoordinateConverter.fromCoordinate(coordinate);
        assertEquals(expectedStr, actualStr);
    }

    /**
     * Tests that toCoordinate converts a comma-separated String into a Coordinate.
     */
    @Test
    public void testToCoordinate() {
        float lat = -39.85f;
        float lan = 81.2f;
        Coordinate expectedCoordinate = new Coordinate(lat, lan);
        String coordinateStr = CoordinateConverter.fromCoordinate(expectedCoordinate);

        Coordinate actualCoordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertEquals(expectedCoordinate, actualCoordinate);
    }

    /**
     * Tests that toCoordinate returns null if the data has too few arguments (one value/bad delimiter)
     */
    @Test
    public void testToCoordinateOneArg() {
        String coordinateStr = "111.234";

        Coordinate coordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertNull(coordinate);


        coordinateStr = "50.23|21.5";

        coordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertNull(coordinate);
    }

    /**
     * Tests that toCoordinate returns null if too many arguments are given.
     */
    @Test
    public void testToCoordinateExtraArgs() {
        String coordinateStr = "1.008,2.115,3.1415";

        Coordinate coordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertNull(coordinate);
    }

    /**
     * Tests that toCoordinate returns null if either value cannot be parsed to a float.
     */
    @Test
    public void testToCoordinateNaN() {
        String coordinateStr = "; SELECT * FROM users; --,44.4";

        Coordinate coordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertNull(coordinate);

        coordinateStr = "1.008,Twenty-Nine";

        coordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertNull(coordinate);
    }

}
