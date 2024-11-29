package com.example.swen766_bettermaps.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.User;

/**
 * A database using Room for the BetterMaps application.
 * Initialize the singleton instance with <code>Room.databaseBuilder()</code>
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class BMDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "bm_database";

    public abstract UserDAO userDAO();
}
