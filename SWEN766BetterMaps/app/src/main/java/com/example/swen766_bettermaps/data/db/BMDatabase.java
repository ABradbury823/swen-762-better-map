package com.example.swen766_bettermaps.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;

/**
 * A database using Room for the BetterMaps application.
 * <br>Initialize the singleton instance with <code>Room.databaseBuilder()</code>
 */
@Database(entities = {
    User.class,
    UserFavoriteLocation.class,
    Location.class,
    LocationAmenity.class,
    Amenity.class
},
    version = 1, exportSchema = false)
public abstract class BMDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "bm_database";

    public abstract UserDAO userDAO();

    public abstract LocationDAO locationDAO();
}
