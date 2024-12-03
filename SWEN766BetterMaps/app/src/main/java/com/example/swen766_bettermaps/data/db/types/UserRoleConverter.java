package com.example.swen766_bettermaps.data.db.types;

import androidx.room.TypeConverter;

/**
 * Class responsible for converting the UserRole enum into a database-safe String entry.
 */
public class UserRoleConverter {
    // convert UserRole to String (for db storage)
    @TypeConverter
    public static String fromUserRole(UserRole userRole) {
        if(userRole == null) return null;
        return userRole.name();
    }

    // convert String back to UserRole (reading from db)
    @TypeConverter
    public static UserRole toUserRole(String userRole) {
        try {
            return UserRole.valueOf(userRole);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.out.println("No user role matches the String '" + userRole + "'");
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            System.out.println("User role String was null");
        }
        return null;
    }
}
