package com.example.swen766_bettermaps.data.db.types;

import androidx.room.TypeConverter;

/**
 * Class responsible for converting the Coordinate class into a database-safe String entry.
 */
public class CoordinateConverter {
    // convert Coordinate to String (for db storage)
    @TypeConverter
    public static String fromCoordinate(Coordinate coordinate) {
        if(coordinate == null) return null;
        return coordinate.getLatitude() + "," + coordinate.getLongitude();
    }

    // convert String to Coordinate (reading from db)
    @TypeConverter
    public static Coordinate toCoordinate(String coordinate) {
        // split entry by comma
        try {
            String[] parts = coordinate.split(",");
            if(parts.length == 2) {
            // convert lat. and long. to float
                float latitude = Float.parseFloat(parts[0]);
                float longitude = Float.parseFloat(parts[1]);
                return new Coordinate(latitude, longitude);
        }
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            System.out.println("Converter: latitude and longitude must be floats");
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            System.out.println("Coordinate String was null");
        }
        return null;    // invalid length or bad parse
    }
}
