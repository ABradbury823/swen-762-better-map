package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.types.Coordinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocationDAOTest {
    private BMDatabase database;
    private LocationDAO locationDAO;

    // create db before each test
    @Before
    public void setUp() {
        // create in-memory database for testing
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, BMDatabase.class)
            .allowMainThreadQueries() // main thread use for testing only
            .build();

        locationDAO = database.locationDAO();
    }

    // clean up db after each test
    @After
    public void tearDown() {
        database.close();
    }

    /**
     * Tests that insert adds a location and get retrieves it.
     */
    @Test
    public void testInsertAndGet() {
        Coordinate coordinate = new Coordinate(50.0f, -25.0f);
        Location location = new Location("Location Name", "Location Description",
            "Location Address", coordinate);

        locationDAO.insert(location);

        Location retrievedLocation = locationDAO.getLocationById(1);

        assertNotNull(retrievedLocation);
        assertEquals(location.getName(), retrievedLocation.getName());
        assertEquals(location.getDescription(), retrievedLocation.getDescription());
        assertEquals(location.getAddress(), retrievedLocation.getAddress());
        assertEquals(location.getCoordinates(), retrievedLocation.getCoordinates());
    }
}
