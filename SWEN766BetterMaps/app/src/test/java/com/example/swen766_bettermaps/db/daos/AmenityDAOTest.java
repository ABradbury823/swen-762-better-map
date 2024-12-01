package com.example.swen766_bettermaps.db.daos;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;

import org.junit.After;
import org.junit.Before;

public class AmenityDAOTest {

    private BMDatabase database;
    private AmenityDAO amenityDAO;

    // create db before each test
    @Before
    public void setUp() {
        // create in-memory database for testing
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, BMDatabase.class)
            .allowMainThreadQueries() // main thread use for testing only
            .build();

        amenityDAO = database.amenityDAO();
    }

    // clean up db after each test
    @After
    public void tearDown() {
        database.close();
    }
}
