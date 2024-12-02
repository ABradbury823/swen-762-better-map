package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.joins.AmenityWithIncludedLocations;
import com.example.swen766_bettermaps.data.db.types.Coordinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AmenityDAOTest {

    private BMDatabase database;
    private AmenityDAO amenityDAO;
    private LocationDAO locationDAO;

    // create db before each test
    @Before
    public void setUp() {
        // create in-memory database for testing
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, BMDatabase.class)
            .allowMainThreadQueries() // main thread use for testing only
            .build();

        amenityDAO = database.amenityDAO();
        locationDAO = database.locationDAO();
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

        long id = amenityDAO.insert(amenity);

        Amenity retrievedAmenity = amenityDAO.getAmenityById(id);

        assertNotNull(retrievedAmenity);
        assertEquals(amenity.getName(), retrievedAmenity.getName());
        assertEquals(amenity.getDescription(), retrievedAmenity.getDescription());
    }

    //TODO: test getById gets the list of includedLocations (need Location DAO tests first)
    /**
     * Tests that getById also retrieves a list of included Locations.
     */
    @Test
    public void testGetIncludedLocations() {
        Location[] locations = {
            new Location("Location 1", "Location 1",
                "Location 1 Address", new Coordinate(25.0f, 25.0f)),
            new Location("Location 2", "Location 2",
                "Location 2 Address", new Coordinate(17.8f, -97.3f)),
            new Location("Location 3", "Location 3",
                "Location 3 Address", new Coordinate()),
        };
        for(Location l : locations) {
            locationDAO.insert(l);
        }
        Amenity amenity = new Amenity("Amenity Name", "Amenity Desc.");
        long id = amenityDAO.insert(amenity);

        List<Location> dbLocations = locationDAO.getAllLocations();

        AmenityWithIncludedLocations amenityWithIncludedLocations =
            amenityDAO.getAmenityWithIncludedLocations(id);
        assertEquals(0, amenityWithIncludedLocations.includedLocations.size());

        for(Location l : dbLocations) {
            LocationAmenity locationAmenity =
                new LocationAmenity(l.getId(), id);
            locationDAO.insertLocationAmenity(locationAmenity);
        }

        amenityWithIncludedLocations = amenityDAO.getAmenityWithIncludedLocations(id);
        List<Location> includedLocations = amenityWithIncludedLocations.includedLocations;
        for(int i = 0; i < dbLocations.size(); i++) {
            assertEquals(dbLocations.get(i).getId(), includedLocations.get(i).getId());
            assertEquals(dbLocations.get(i).getName(), includedLocations.get(i).getName());
            assertEquals(dbLocations.get(i).getDescription(), includedLocations.get(i).getDescription());
            assertEquals(dbLocations.get(i).getAddress(), includedLocations.get(i).getAddress());
            assertEquals(dbLocations.get(i).getCoordinates(), includedLocations.get(i).getCoordinates());
        }
    }

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
     * Tests that update changes an existing Amenity.
     */
    @Test
    public void testUpdate() {
        Amenity amenity = new Amenity("Test Name", "Test Description");
        long id = amenityDAO.insert(amenity);
        Amenity dbAmenity = amenityDAO.getAmenityById(id);
        String newName = "New Amenity Name";
        String newDesc = "New Amenity Description";

        dbAmenity.setName(newName);
        dbAmenity.setDescription(newDesc);

        amenityDAO.update(dbAmenity);

        Amenity updatedAmenity = amenityDAO.getAmenityById(id);
        assertEquals(dbAmenity.getName(), updatedAmenity.getName());
        assertEquals(dbAmenity.getDescription(), updatedAmenity.getDescription());
    }

    /**
     * Tests that updating an amenity that does not exist does nothing
     */
    @Test
    public void testUpdateDoesNotExist() {
        Amenity amenity = new Amenity("Test Name", "Test Description");
        long id = amenityDAO.insert(amenity);
        Amenity dbAmenity = amenityDAO.getAmenityById(id);
        String newName = "New Amenity Name";
        String newDesc = "New Amenity Description";

        dbAmenity.setId(10);    // does not exist
        dbAmenity.setName(newName);
        dbAmenity.setDescription(newDesc);

        amenityDAO.update(dbAmenity);

        Amenity updatedAmenity = amenityDAO.getAmenityById(id + 1);
        assertNull(updatedAmenity);
    }


    /**
     * Tests that delete removes an Amenity from the database.
     */
    @Test
    public void testDelete() {
        Amenity amenity = new Amenity("Test Name", "Test Description");
        long id = amenityDAO.insert(amenity);
        Amenity dbAmenity = amenityDAO.getAmenityById(id);

        amenityDAO.delete(dbAmenity);

        Amenity deletedAmenity = amenityDAO.getAmenityById(id);
        assertNull(deletedAmenity);
    }

    /**
     * Tests that deleting an amenity that does not exist does nothing.
     */
    @Test
    public void testDeleteDoesNotExist() {
        Amenity[] amenities = {
            new Amenity("Amenity 1", "Amenity 1 Description"),
            new Amenity("Amenity 2", "Amenity 2 Description"),
            new Amenity("Amenity 3", "Amenity 3 Description")
        };
        for(Amenity a : amenities) {
            amenityDAO.insert(a);
        }
        Amenity nonExistentAmenity = new Amenity("Amenity Name", "Amenity Desc.");
        nonExistentAmenity.setId(10);
        amenityDAO.delete(nonExistentAmenity);

        List<Amenity> retrievedAmenities = amenityDAO.getAllAmenities();
        assertEquals(amenities.length, retrievedAmenities.size());

    }
}
