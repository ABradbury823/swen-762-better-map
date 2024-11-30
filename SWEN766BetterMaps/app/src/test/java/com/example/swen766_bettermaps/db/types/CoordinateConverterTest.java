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
}
