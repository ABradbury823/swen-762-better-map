package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;
import com.example.swen766_bettermaps.data.db.entities.joins.AmenityWithIncludedLocations;
import com.example.swen766_bettermaps.data.db.entities.joins.LocationWithFavoriteUsersAndAmenities;
import com.example.swen766_bettermaps.data.db.types.Coordinate;
import com.example.swen766_bettermaps.data.db.types.UserRole;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LocationDAOTest {
    private BMDatabase database;
    private AmenityDAO amenityDAO;
    private LocationDAO locationDAO;
    private UserDAO userDAO;

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
        userDAO = database.userDAO();
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

    /**
     * Tests that insertLocationAmenity adds a connection between a location and an Amenity
     */
    @Test
    public void testInsertAndGetLocationAmenity() {
        Location location = new Location("Location", "Desc.",
            "Address", new Coordinate());
        long lid = locationDAO.insert(location);
        Amenity amenity = new Amenity("Amenity", "Amenity Desc.");
        long aid = amenityDAO.insert(amenity);

        LocationWithFavoriteUsersAndAmenities locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        AmenityWithIncludedLocations amenityWithIncludedLocations =
            amenityDAO.getAmenityWithIncludedLocations(aid);
        assertEquals(0, locationWithFavoriteUsersAndAmenities.amenities.size());
        assertEquals(0, amenityWithIncludedLocations.includedLocations.size());

        LocationAmenity locationAmenity =
            new LocationAmenity(lid, aid);
        locationDAO.insertLocationAmenity(locationAmenity);

        LocationAmenity received_la = locationDAO.getLocationAmenity(lid, aid);
        assertNotNull(received_la);
        assertEquals(lid, received_la.getLocationId());
        assertEquals(aid, received_la.getAmenityId());

         locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
         amenityWithIncludedLocations = amenityDAO.getAmenityWithIncludedLocations(aid);
        assertEquals(1, locationWithFavoriteUsersAndAmenities.amenities.size());
        assertEquals(1, amenityWithIncludedLocations.includedLocations.size());
    }

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

    /**
     * Tests that getLocationWithFavoriteUsersAndAmenities retrieves a list of
     * favorite users and amenities
     */
    @Test
    public void testGetLocationWithFavoriteUsersAndAmenities() {
        User[] users = {
            new User("User 1", "user1@email.com", UserRole.STUDENT),
            new User("User 2", "user2@email.com", UserRole.ADMIN),
            new User("User 3", "user3@email.com", UserRole.FACULTY),
        };
        long[] uIds = new long[users.length];
        for(int i = 0; i < users.length; i++) {
            uIds[i] = userDAO.insert(users[i]);
        }
        Amenity[] amenities = {
            new Amenity("Amenity 1", "Amenity 1 Description"),
            new Amenity("Amenity 2", "Amenity 2 Description"),
            new Amenity("Amenity 3", "Amenity 3 Description")
        };
        long[] aIds = new long[amenities.length];
        for(int i = 0; i < amenities.length; i++) {
            aIds[i] = amenityDAO.insert(amenities[i]);
        }
        Location location = new Location("Location", "Description",
            "Address", new Coordinate());
        long id = locationDAO.insert(location);

        LocationWithFavoriteUsersAndAmenities locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(id);
        assertEquals(0, locationWithFavoriteUsersAndAmenities.favoriteUsers.size());
        assertEquals(0, locationWithFavoriteUsersAndAmenities.amenities.size());

        for(long u : uIds) {
            UserFavoriteLocation userFavoriteLocation =
                new UserFavoriteLocation(u, id);
            userDAO.insertFavoriteLocation(userFavoriteLocation);
        }
        for(long a : aIds) {
            LocationAmenity locationAmenity =
                new LocationAmenity(id, a);
            locationDAO.insertLocationAmenity(locationAmenity);
        }

        locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(id);
        List<User> favoriteUsers = locationWithFavoriteUsersAndAmenities.favoriteUsers;
        List<Amenity> locationAmenities = locationWithFavoriteUsersAndAmenities.amenities;
        assertEquals(users.length, favoriteUsers.size());
        assertEquals(amenities.length, locationAmenities.size());
        for(int i = 0; i < users.length; i++) {
            assertEquals(uIds[i], favoriteUsers.get(i).getId());
            assertEquals(users[i].getUsername(), favoriteUsers.get(i).getUsername());
            assertEquals(users[i].getEmail(), favoriteUsers.get(i).getEmail());
            assertEquals(users[i].getRole(), favoriteUsers.get(i).getRole());
        }
        for(int i = 0; i < amenities.length; i++) {
            assertEquals(aIds[i], locationAmenities.get(i).getId());
            assertEquals(amenities[i].getName(), locationAmenities.get(i).getName());
            assertEquals(amenities[i].getDescription(), locationAmenities.get(i).getDescription());
        }
    }

    /**
     * Tests that update changes the values of a Location.
     */
    @Test
    public void testUpdate() {
        Location location = new Location("Location Name", "Location Description",
            "Location Address", new Coordinate(15.0f, -1.53f));
        long id = locationDAO.insert(location);

        location = locationDAO.getLocationById(id);
        location.setName("New Location Name");
        location.setDescription("New Location Description");
        location.setAddress("New Location Address");
        location.setCoordinates(43.7f, 74.3f);
        locationDAO.update(location);

        Location retrievedLocation = locationDAO.getLocationById(id);
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
        long id = locationDAO.insert(location);

        location = locationDAO.getLocationById(id);

        locationDAO.delete(location);

        Location deletedLocation = locationDAO.getLocationById(id);
        assertNull(deletedLocation);
    }

    /**
     * Tests that deleteLocationAmenity removes an amenity from the location.
     */
    @Test
    public void testDeleteLocationAmenity() {
        Amenity amenity = new Amenity("Amenity", "Description");
        long aid = amenityDAO.insert(amenity);

        Location location = new Location("Location", "Description",
            "Address", new Coordinate());
        long lid = locationDAO.insert(location);
        LocationAmenity locationAmenity = new LocationAmenity(lid, aid);
        locationDAO.insertLocationAmenity(locationAmenity);

        LocationWithFavoriteUsersAndAmenities locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        AmenityWithIncludedLocations amenityWithIncludedLocations =
            amenityDAO.getAmenityWithIncludedLocations(aid);
        assertEquals(1, locationWithFavoriteUsersAndAmenities.amenities.size());
        assertEquals(1, amenityWithIncludedLocations.includedLocations.size());

        locationDAO.deleteLocationAmenity(locationAmenity);
        locationAmenity = locationDAO.getLocationAmenity(lid, aid);
        assertNull(locationAmenity);

        locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        amenityWithIncludedLocations = amenityDAO.getAmenityWithIncludedLocations(aid);
        assertEquals(0, locationWithFavoriteUsersAndAmenities.amenities.size());
        assertEquals(0, amenityWithIncludedLocations.includedLocations.size());
    }

}
