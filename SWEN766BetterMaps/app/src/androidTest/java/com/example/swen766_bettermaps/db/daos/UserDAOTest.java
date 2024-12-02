package com.example.swen766_bettermaps.db.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;
import com.example.swen766_bettermaps.data.db.entities.joins.LocationWithFavoriteUsersAndAmenities;
import com.example.swen766_bettermaps.data.db.entities.joins.UserWithFavoriteLocations;
import com.example.swen766_bettermaps.data.db.types.Coordinate;
import com.example.swen766_bettermaps.data.db.types.UserRole;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDAOTest {
    private BMDatabase database;
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

        locationDAO = database.locationDAO();
        userDAO = database.userDAO();
    }

    // clean up db after each test
    @After
    public void tearDown() {
        database.close();
    }

    /**
     * Tests that insert adds a user to the table and get retrieves a user by id.
     */
    @Test
    public void testInsertAndGet() {
        User user = new User("Example Name", "example@email.com", UserRole.STUDENT);

        long id = userDAO.insert(user);

        User retrievedUser = userDAO.getUserById(id);

        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    /**
     * Tests that insertUserFavorite location adds a connection between user and location.
     */
    @Test
    public void testInsertAndGetUserFavoriteLocation() {
        User user = new User("User Name", "user@email.com", UserRole.FACULTY);
        long uid = userDAO.insert(user);

        Location location = new Location("Location", "Location Desc.",
            "Location Address", new Coordinate());
        long lid = locationDAO.insert(location);

        UserWithFavoriteLocations userWithFavoriteLocations =
            userDAO.getUserWithFavoriteLocations(uid);
        LocationWithFavoriteUsersAndAmenities locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        assertEquals(0, userWithFavoriteLocations.favoriteLocations.size());
        assertEquals(0, locationWithFavoriteUsersAndAmenities.favoriteUsers.size());

        UserFavoriteLocation ufl = new UserFavoriteLocation(uid, lid);
        userDAO.insertFavoriteLocation(ufl);

        UserFavoriteLocation r_ufl = userDAO.getUserFavoriteLocation(uid, lid);
        assertNotNull(r_ufl);
        assertEquals(r_ufl.getUserId(), uid);
        assertEquals(r_ufl.getLocationId(), lid);

         userWithFavoriteLocations = userDAO.getUserWithFavoriteLocations(uid);
         locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        assertEquals(1, userWithFavoriteLocations.favoriteLocations.size());
        assertEquals(1, locationWithFavoriteUsersAndAmenities.favoriteUsers.size());
    }

    /**
     * Tests that getAllUsers retrieves a list of every user.
     */
    @Test
    public void testGetAllUsers() {
        User[] users = {
            new User("User 1", "user1@email.com", UserRole.STUDENT),
            new User("User 2", "user2@email.com", UserRole.FACULTY),
            new User("User 3", "user3@email.com", UserRole.ADMIN)
        };
        for(User u : users) {
            userDAO.insert(u);
        }

        List<User> retrievedUsers = userDAO.getAllUsers();

        assertNotNull(retrievedUsers);
        assertEquals(users.length, retrievedUsers.size());
        for(int i = 0; i < retrievedUsers.size(); i++) {
            assertEquals(users[i].getUsername(), retrievedUsers.get(i).getUsername());
            assertEquals(users[i].getEmail(), retrievedUsers.get(i).getEmail());
            assertEquals(users[i].getRole(), retrievedUsers.get(i).getRole());
        }
    }

    /**
     * Tests that getUserWithFavoriteLocations can retrieve a list of favorite locations.
     */
    @Test
    public void testGetUserWithFavoriteLocations() {
        Location[] locations ={
            new Location("Location 1 Name", "Location 1 Desc.",
                "Location 1 Address", new Coordinate()),
            new Location("Location 2 Name", "Location 2 Desc.",
                "Location 2 Address", new Coordinate()),
            new Location("Location 3 Name", "Location 3 Desc.",
                "Location 3 Address", new Coordinate()),
        };
        for(Location l : locations) {
            locationDAO.insert(l);
        }
        User user = new User("User 1", "user1@email.com", UserRole.STUDENT);
        long id = userDAO.insert(user);

        List<Location> dbLocations = locationDAO.getAllLocations();
        user = userDAO.getUserById(id);

        UserWithFavoriteLocations userWithFavoriteLocations =
            userDAO.getUserWithFavoriteLocations(user.getId());

        assertEquals(0, userWithFavoriteLocations.favoriteLocations.size());

        for(Location l : dbLocations) {
            UserFavoriteLocation ufl =
                new UserFavoriteLocation(user.getId(), l.getId());
            userDAO.insertFavoriteLocation(ufl);
        }

        userWithFavoriteLocations =
            userDAO.getUserWithFavoriteLocations(user.getId());
        List<Location> favLocations = userWithFavoriteLocations.favoriteLocations;
        assertEquals(dbLocations.size(), userWithFavoriteLocations.favoriteLocations.size());
        for(int i = 0; i < dbLocations.size(); i++) {
            assertEquals(dbLocations.get(i).getId(), favLocations.get(i).getId());
            assertEquals(dbLocations.get(i).getName(), favLocations.get(i).getName());
            assertEquals(dbLocations.get(i).getDescription(), favLocations.get(i).getDescription());
            assertEquals(dbLocations.get(i).getAddress(), favLocations.get(i).getAddress());
            assertEquals(dbLocations.get(i).getCoordinates(), favLocations.get(i).getCoordinates());
        }
    }

    /**
     * Tests that update changes the values of a user
     */
    @Test
    public void testUpdate() {
        User user = new User("New User", "newuser@email.com", UserRole.NONE);
        long id = userDAO.insert(user);

        user = userDAO.getUserById(id);
        user.setUsername("New Name");
        user.setEmail("newname@email.com");
        user.setRole(UserRole.FACULTY);
        userDAO.update(user);

        User retrievedUser = userDAO.getUserById(id);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    /**
     * Tests that delete removes a user.
     */
    @Test
    public void testDelete() {
        User user = new User("Delete Me", "deleted@email.com", UserRole.ADMIN);
        long id = userDAO.insert(user);

        user = userDAO.getUserById(id);

        userDAO.delete(user);

        User deletedUser = userDAO.getUserById(id);
        assertNull(deletedUser);
    }

    /**
     * Tests that deleteFavoriteLocation removes a favorite location from the user.
     */
    @Test
    public void testDeleteFavoriteLocation() {
        User user = new User("User Name", "user@email.com", UserRole.FACULTY);
        long uid = userDAO.insert(user);

        Location location = new Location("Location", "Location Desc.",
            "Location Address", new Coordinate());
        long lid = locationDAO.insert(location);

        UserFavoriteLocation ufl = new UserFavoriteLocation(uid, lid);
        userDAO.insertFavoriteLocation(ufl);

        UserWithFavoriteLocations userWithFavoriteLocations =
            userDAO.getUserWithFavoriteLocations(uid);
        LocationWithFavoriteUsersAndAmenities locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        assertEquals(1, userWithFavoriteLocations.favoriteLocations.size());
        assertEquals(1, locationWithFavoriteUsersAndAmenities.favoriteUsers.size());

        userDAO.deleteFavoriteLocation(ufl);
        ufl = userDAO.getUserFavoriteLocation(uid, lid);
        assertNull(ufl);

        userWithFavoriteLocations = userDAO.getUserWithFavoriteLocations(uid);
        locationWithFavoriteUsersAndAmenities =
            locationDAO.getLocationWithFavoriteUsersAndAmenities(lid);
        assertEquals(0, userWithFavoriteLocations.favoriteLocations.size());
        assertEquals(0, locationWithFavoriteUsersAndAmenities.favoriteUsers.size());
    }
}
