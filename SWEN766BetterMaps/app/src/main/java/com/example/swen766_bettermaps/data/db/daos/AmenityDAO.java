package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.joins.AmenityWithIncludedLocations;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the amenities table.
 */
@Dao
public interface AmenityDAO {

    /**
     * Inserts a new amenity into the amenities table.
     * @param amenity The new amenity.
     * @return The auto-generated id of the new amenity.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Amenity amenity);

    /**
     * Retrieves all amenities from the amenities table.
     * @return A list containing all amenities.
     */
    @Query("SELECT * FROM amenities")
    List<Amenity> getAllAmenities();

    /**
     * Retrieves an amenity from the amenities table based on its id.
     * @param amenityId The amenity's id.
     * @return The amenity that matches the id,
     * or null if the id does not match to an amenity.
     */
    @Query("SELECT * FROM amenities WHERE id = :amenityId")
    Amenity getAmenityById(long amenityId);

    /**
     * Retrieves an amenity from the amenities table based on its id.
     * <br>Also retrieves the amenity's included locations.
     * @param amenityId The amenity's id.
     * @return The amenity that matches the id,
     * or null if the id does not match to an amenity.
     */
    @Transaction
    @Query("SELECT * FROM amenities WHERE id = :amenityId")
    AmenityWithIncludedLocations getAmenityWithIncludedLocations(long amenityId);

    /**
     * Updates an amenity in the amenities table.
     * @param amenity The amenity to update. Primary key must match to an existing amenity.
     */
    @Update
    void update(Amenity amenity);

    /**
     * Removes an amenity from the amenities table.
     * @param amenity The amenity to update. Primary key must match to an existing amenities.
     */
    @Delete
    void delete(Amenity amenity);
}
