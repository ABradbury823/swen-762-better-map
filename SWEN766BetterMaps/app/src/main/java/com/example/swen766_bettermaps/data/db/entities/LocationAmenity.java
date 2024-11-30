package com.example.swen766_bettermaps.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * A join table between Locations and their Amenities.
 */
@Entity(
    tableName = "location_amenities",
    primaryKeys = {"location_id", "amenity_id"},
    foreignKeys = {
        @ForeignKey(
            entity = Location.class,
            parentColumns = "id",
            childColumns = "location_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Amenity.class,
            parentColumns = "id",
            childColumns = "amenity_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class LocationAmenities {
    @ColumnInfo(name = "location_id")
    private final int locationId;

    @ColumnInfo(name = "amenity_id")
    private final int amenityId;

    /**
     * Constructs a join relationship between a Location and an Amenity.
     * @param locationId The id of the Location.
     * @param amenityId The id of the Amenity.
     */
    public LocationAmenities(int locationId, int amenityId) {
        this.locationId = locationId;
        this.amenityId = amenityId;
    }

    public int getLocationId() { return locationId; }

    public int getAmenityId() { return amenityId; }
}