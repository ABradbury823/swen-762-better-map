package com.example.swen766_bettermaps.data.db.types;

import androidx.room.TypeConverter;

/**
 * Class responsible for converting the UserRole enum into a database-safe String entry.
 */
public class UserRoleConverter {
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
}
