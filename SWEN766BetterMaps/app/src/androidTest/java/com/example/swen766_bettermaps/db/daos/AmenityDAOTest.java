package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    /**
     * Tests that insert adds an amenity to the table and get retrieves an element by id.
     */
    @Test
    public void testInsertAndGet() {
        Amenity amenity = new Amenity("Test Name", "Test Description");

        amenityDAO.insert(amenity);

        Amenity retrievedAmenity = amenityDAO.getAmenityById(1);

        assertNotNull(retrievedAmenity);
        assertEquals(amenity.getName(), retrievedAmenity.getName());
        assertEquals(amenity.getDescription(), retrievedAmenity.getDescription());
    }

    //TODO: test getById gets the list of includedLocations (need Location DAO tests first)

    /**
     * Tests that get all retrieves all elements from the amenities table.
     */
    @Test
    public void testGetAll() {
        Amenity[] amenities = {
            new Amenity("Amenity 1", "Amenity 1 Description"),
            new Amenity("Amenity 2", "Amenity 2 Description"),
            new Amenity("Amenity 3", "Amenity 3 Description")
        };
        for(Amenity a : amenities) {
            amenityDAO.insert(a);
        }

        List<Amenity> retrievedAmenities = amenityDAO.getAllAmenities();
        assertNotNull(retrievedAmenities);
        assertNotEquals(0, retrievedAmenities.size());
        for(int i = 0; i < retrievedAmenities.size(); i++) {
            Amenity ra = retrievedAmenities.get(i);
            assertNotNull(amenities[i].getName(), ra.getName());
            assertEquals(amenities[i].getDescription(), ra.getDescription());
        }
    }

    /**
     *
     */
}
