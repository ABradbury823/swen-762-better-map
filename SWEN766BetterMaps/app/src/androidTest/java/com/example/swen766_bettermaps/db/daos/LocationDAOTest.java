package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import java.util.List;

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

    //TODO: test insertLocationAmenity

    /**
     * Tests that getAllLocations retrieves all locations.
     */
    @Test
    public void testGetAllLocations() {
        Location[] locations = {
            new Location("Location 1", "Location 1 Desc.", "Location 1 Address", new Coordinate(50.0f, 50.0f)),
            new Location("Location 1", "Location 1 Desc.", "Location 1 Address", new Coordinate(85.0f, -120.0f)),
            new Location("Location 1", "Location 1 Desc.", "Location 1 Address", new Coordinate(-23.0f, 17.182f))
        };
        for(Location l : locations) {
            locationDAO.insert(l);
        }

        List<Location> retrievedLocations = locationDAO.getAllLocations();
        assertNotNull(retrievedLocations);
        assertEquals(locations.length, retrievedLocations.size());
        for(int i = 0; i < retrievedLocations.size(); i++) {
            assertEquals(locations[i].getName(), retrievedLocations.get(i).getName());
            assertEquals(locations[i].getDescription(), retrievedLocations.get(i).getDescription());
            assertEquals(locations[i].getAddress(), retrievedLocations.get(i).getAddress());
            assertEquals(locations[i].getCoordinates(), retrievedLocations.get(i).getCoordinates());
        }

    }

    //TODO: test getLocationAmenity
    //TODO: test that getByID retrieves a list of favoriteUsers and amenities

    /**
     * Tests that update changes the values of a Location.
     */
    @Test
    public void testUpdate() {
        Location location = new Location("Location Name", "Location Description",
            "Location Address", new Coordinate(15.0f, -1.53f));
        locationDAO.insert(location);

        location = locationDAO.getLocationById(1);
        location.setName("New Location Name");
        location.setDescription("New Location Description");
        location.setAddress("New Location Address");
        location.setCoordinates(43.7f, 74.3f);
        locationDAO.update(location);

        Location retrievedLocation = locationDAO.getLocationById(1);
        assertEquals(location.getName(), retrievedLocation.getName());
        assertEquals(location.getDescription(), retrievedLocation.getDescription());
        assertEquals(location.getAddress(), retrievedLocation.getAddress());
        assertEquals(location.getCoordinates(), retrievedLocation.getCoordinates());
    }

    /**
     * Tests that delete removes a Location.
     */
    @Test
    public void testDelete() {
        Location location = new Location();
        locationDAO.insert(location);

        location = locationDAO.getLocationById(1);

        locationDAO.delete(location);

        Location deletedLocation = locationDAO.getLocationById(1);
        assertNull(deletedLocation);
    }

    // TODO: test deleteLocationAmenity
}
