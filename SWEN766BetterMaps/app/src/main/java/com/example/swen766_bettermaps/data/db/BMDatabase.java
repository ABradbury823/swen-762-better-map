package com.example.swen766_bettermaps.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;

/**
 * A database using Room for the BetterMaps application.
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

    // alert all threads of changed value
    private static volatile BMDatabase INSTANCE;

    public static BMDatabase getInstance(final Context context) {
        // instance already created?
        if(INSTANCE == null) {
            synchronized (BMDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BMDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDAO userDAO();

    public abstract LocationDAO locationDAO();

    public abstract AmenityDAO amenityDAO();
}
