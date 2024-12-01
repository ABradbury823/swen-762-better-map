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

        userDAO.insert(user);

        User retrievedUser = userDAO.getUserById(1);

        assertNotNull(retrievedUser);
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    // TODO: test insert favorite location (test Location first)

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

    // TODO: test getById retrieves the list of favorite locations (test LocationDAO first)
    /**
     * Tests that getById retrieves a list of favorite Locations.
     */
    @Test
    public void testGetFavoriteLocations() {
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
        userDAO.insert(user);

        List<Location> dbLocations = locationDAO.getAllLocations();
        user = userDAO.getUserById(1);

        // this needs a test for inserting user favorites, hmmm cyclical...
    }


    // TODO: test getFavoriteLocation

    /**
     * Tests that update changes the values of a user
     */
    @Test
    public void testUpdate() {
        User user = new User("New User", "newuser@email.com", UserRole.NONE);
        userDAO.insert(user);

        user = userDAO.getUserById(1);
        user.setUsername("New Name");
        user.setEmail("newname@email.com");
        user.setRole(UserRole.FACULTY);
        userDAO.update(user);

        User retrievedUser = userDAO.getUserById(1);
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
        userDAO.insert(user);

        user = userDAO.getUserById(1);

        userDAO.delete(user);

        User deletedUser = userDAO.getUserById(1);
        assertNull(deletedUser);
    }

    //TODO: test deleteFavoriteLocation

}
