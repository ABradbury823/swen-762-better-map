package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.User;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the users table.
 */
@Dao
public interface UserDAO {

    /**
     * Inserts a new user into the users table.
     * @param user The new user.
     */
    @Insert
    void insert(User user);

    /**
     * Retrieves all users from the users table.
     * @return A list containing all users.
     */
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    /**
     * Retrieves a user from the users table based on their id.
     * @param userId The user's ID.
     * @return The user, or null if the id does not match to a user.
     */
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    User getUserById(int userId);

    /**
     * Retrieves a user from the users table based on their username.
     * @param username The user's username.
     * @return The first user that matches the username,
     * or null if the username does not match to a user.
     */
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    /**
     * Retrieves a user from the users table based on their email.
     * @param email The user's email.
     * @return The first user that matches the email,
     * or null if the email does not match to a user.
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    /**
     * Updates a user in the users table.
     * @param user The user to update. Primary key must match to an existing user.
     */
    @Update
    void update(User user);

    /**
     * Deletes a user from the users table.
     * @param user The user to delete. Primary key must match to an existing user.
     */
    @Delete
    void delete(User user);
}
