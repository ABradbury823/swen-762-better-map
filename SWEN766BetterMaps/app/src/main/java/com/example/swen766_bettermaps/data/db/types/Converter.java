package com.example.swen766_bettermaps.data.db.types;

import androidx.room.TypeConverter;

import com.example.swen766_bettermaps.data.db.entities.User;

public class Converter {
    // convert UserRole to String (for db storage)
    @TypeConverter
    public static String fromUserRole(UserRole userRole) {
        return userRole.name();
    }

    // convert String back to UserRole (reading from db)
    @TypeConverter
    public static UserRole toUserRole(String userRole) {
        return UserRole.valueOf(userRole);
    }

    // convert Coordinate to String (for db storage)
    @TypeConverter
    public static String fromCoordinate(Coordinate coordinate) {
        return coordinate.getLatitude() + "," + coordinate.getLongitude();
    }

    // convert String to Coordinate (reading from db)
    @TypeConverter
    public static Coordinate toCoordinate(String coordinate) {
        // split entry by comma
        String[] parts = coordinate.split(",");
        if(parts.length == 2) {
            // convert lat. and long. to float
            try {
                float latitude = Float.parseFloat(parts[0]);
                float longitude = Float.parseFloat(parts[1]);
                return new Coordinate(latitude, longitude);
            } catch(NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
                System.out.println("Converter: latitude and longitude must be floats");
            }
        }
        return null;    // invalid length or bad parse
    }
}
