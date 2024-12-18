package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.joins.LocationWithFavoriteUsersAndAmenities;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the locations table.
 */
@Dao
public interface LocationDAO {

    /**
     * Inserts a new location into the locations table.
     * @param location The new location.
     * @return The auto-generated id of the new location.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Location location);

    /**
     * Inserts a new amenity for a location into the location_amenities table.
     * @param locationAmenity A link between an existing location and an existing amenity
     *                             in the format (locationId, amenityId).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocationAmenity(LocationAmenity locationAmenity);

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
    @Query("SELECT * FROM locations WHERE id = :locationId")
    Location getLocationById(long locationId);

    /**
     * Retrieves a connection between a location and an amenity from the location_amenities table.
     * @param locationId The id of the location.
     * @param amenityId The id of the amenity.
     * @return A connection between a location and an amenity. Null if there is no connection.
     */
    @Query("SELECT * FROM location_amenities " +
        "WHERE location_id = :locationId AND amenity_id = :amenityId")
    LocationAmenity getLocationAmenity(long locationId, long amenityId);

    /**
     * Retrieves a location from the locations table based on its id.
     * <br>Also retrieves the location's favorite users and amenities.
     * @param locationId The location's id.
     * @return The location that matches the id,
     * or null if the id does not match to a location.
     */
    @Transaction
    @Query("SELECT * FROM locations WHERE id = :locationId")
    LocationWithFavoriteUsersAndAmenities
        getLocationWithFavoriteUsersAndAmenities(long locationId);

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

    /**
     * Removes an amenity from the location_amenities table.
     * @param locationAmenity A link between an existing location and an existing amenity
     *                             in the format (locationId, amenityId).
     */
    @Delete
    void deleteLocationAmenity(LocationAmenity locationAmenity);
}
