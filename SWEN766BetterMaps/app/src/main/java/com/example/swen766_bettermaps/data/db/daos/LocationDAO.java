package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.Location;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the locations table.
 */
@Dao
public interface LocationDAO {

    /**
     * Inserts a new location into the locations table.
     * @param location The new location.
     */
    @Insert
    void insert(Location location);

    /**
     * Retrieves all locations from the locations table.
     * @return A list containing all locations.
     */
    @Query("SELECT * FROM locations")
    List<Location> getAllLocations();

    /**
     * Retrieves a location from the locations table based on its id.
     * @param locationId The location's id.
     * @return The location that matches the id,
     * or null if the id does not match to a location.
     */
    @Query("SELECT * FROM locations WHERE id = :locationId LIMIT 1")
    Location getLocationById(int locationId);

    /**
     * Updates a location in the locations table.
     * @param location The location to update. Primary key must match to an existing location.
     */
    @Update
    void update(Location location);

    /**
     * Removes a location from the locations table.
     * @param location The location to remove. Primary key must match to an existing location.
     */
    @Delete
    void delete(Location location);
}
