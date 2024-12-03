package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * A join table between Locations and their Amenities.
 */
@Entity(
    tableName = "location_amenities",
    primaryKeys = {"location_id", "amenity_id"}
)
public class LocationAmenity {
    @ColumnInfo(name = "location_id", index = true)
    private long locationId;

    @ColumnInfo(name = "amenity_id", index = true)
    private long amenityId;

    // Default constructor for Room functionality
    public LocationAmenity() {

    }

    /**
     * Constructs a join relationship between a Location and an Amenity.
     * @param locationId The id of the Location.
     * @param amenityId The id of the Amenity.
     */
    @Ignore
    public LocationAmenity(long locationId, long amenityId) {
        this.locationId = locationId;
        this.amenityId = amenityId;
    }

    public long getLocationId() { return locationId; }
    public void setLocationId(long locationId) { this.locationId = locationId; }

    public long getAmenityId() { return amenityId; }
    public void setAmenityId(long amenityId) { this.amenityId = amenityId; }

    @NonNull
    @Override
    @Ignore
    public String toString() {
        return "LocationAmenity{" +
            "locationId: " + locationId +
            ", amenityId: '" + amenityId +
            '}';
    }
}
