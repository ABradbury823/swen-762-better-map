package com.example.swen766_bettermaps.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

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
public class LocationAmenity {
    @ColumnInfo(name = "location_id")
    private int locationId;

    @ColumnInfo(name = "amenity_id")
    private int amenityId;

    // Default constructor for Room functionality
    public LocationAmenity() {

    }

    /**
     * Constructs a join relationship between a Location and an Amenity.
     * @param locationId The id of the Location.
     * @param amenityId The id of the Amenity.
     */
    @Ignore
    public LocationAmenity(int locationId, int amenityId) {
        this.locationId = locationId;
        this.amenityId = amenityId;
    }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) {
        // only change once (after insertion)
        if(this.locationId != 0) return;
        this.locationId = locationId;
    }

    public int getAmenityId() { return amenityId; }
    public void setAmenityId(int amenityId) {
        // only change once (after insertion)
        if(this.amenityId != 0) return;
        this.amenityId = amenityId;
    }
}
