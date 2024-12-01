package com.example.swen766_bettermaps.data.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

/**
 * A join table between Users and their favorite Locations.
 */
@Entity(
    tableName = "favorites",
    primaryKeys = {"user_id", "location_id"},
    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            parentColumns = "id",
            childColumns = "user_id",
            onDelete = ForeignKey.CASCADE),
        @ForeignKey(
            entity = Location.class,
            parentColumns = "id",
            childColumns = "location_id",
            onDelete = ForeignKey.CASCADE)
    },
    // index on foreign keys to improve select, filter, and join queries
    indices = {
        @Index(value = "user_id"),
        @Index(value = "location_id")
    }
)
public class UserFavoriteLocation {

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "location_id")
    private int locationId;

    // Default constructor for Room functionality
    public UserFavoriteLocation() {

    }

    /**
     * Constructs a join relationship between a User and a favorite Location.
     * @param userId The id of the User.
     * @param locationId The id of the Location.
     */
    @Ignore
    public UserFavoriteLocation(int userId, int locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) {
        // only change once (after insertion)
        if(this.userId != 0) return;
        this.userId = userId;
    }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) {
        // only change once (after insertion)
        if(this.locationId != 0) return;
        this.locationId = locationId;
    }
}
