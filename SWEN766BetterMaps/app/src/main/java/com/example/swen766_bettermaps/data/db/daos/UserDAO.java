package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;
import com.example.swen766_bettermaps.data.db.entities.joins.UserWithFavoriteLocations;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the users table.
 */
@Dao
public interface UserDAO {

    /**
     * Inserts a new user into the users table.
     * @param user The new user.
     * @return The auto-generated id of the new user.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    /**
     * Inserts a new favorite location for a user into the favorites table.
     * @param userFavoriteLocation A link between an existing user and an existing location
     *                             in the format (userId, locationId).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteLocation(UserFavoriteLocation userFavoriteLocation);

    /**
     * Retrieves all users from the users table.
     * @return A list containing all users.
     */
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    /**
     * Retrieves a user from the users table based on their id.
     * <br>Also retrieves the user's favorite locations.
     * @param userId The user's ID.
     * @return The user, or null if the id does not match to a user.
     */
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    User getUserById(int userId);

    /**
     * Retrieves a connection between a user and a location from the favorites table.
     * @param userId The id of the user.
     * @param locationId The id of the location.
     * @return A connection between a user and a location. Null if there is no connection.
     */
    @Query("SELECT * FROM favorites WHERE user_id = :userId AND location_id = :locationId")
    UserFavoriteLocation getUserFavoriteLocation(int userId, int locationId);

    /**
     * Retrieves a user from the users table based on their id.
     * <br>Also retrieves the user's favorite locations.
     * @param userId The user's ID.
     * @return The user, or null if the id does not match to a user.
     */
    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    UserWithFavoriteLocations getUserWithFavoriteLocations(int userId);

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

    /**
     * Removes a location from the favorites table.
     * @param userFavoriteLocation A link between an existing user and an existing location
     *                             in the format (userId, locationId).
     */
    @Delete
    void deleteFavoriteLocation(UserFavoriteLocation userFavoriteLocation);
}
