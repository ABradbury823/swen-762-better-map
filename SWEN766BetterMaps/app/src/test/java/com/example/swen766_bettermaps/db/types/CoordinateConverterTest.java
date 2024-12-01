package com.example.swen766_bettermaps.db.types;

import static org.junit.Assert.assertEquals;

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
        float lat = 39.85f;
        float lan = 81.2f;
        Coordinate expectedCoordinate = new Coordinate(lat, lan);
        String coordinateStr = CoordinateConverter.fromCoordinate(expectedCoordinate);

        Coordinate actualCoordinate = CoordinateConverter.toCoordinate(coordinateStr);
        assertEquals(expectedCoordinate, actualCoordinate);
    }

}
