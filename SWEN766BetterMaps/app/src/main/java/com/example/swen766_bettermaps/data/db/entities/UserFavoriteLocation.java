package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * A join table between Users and their favorite Locations.
 */
@Entity(
    tableName = "favorites",
    primaryKeys = {"user_id", "location_id"}
)
public class UserFavoriteLocation {

    @ColumnInfo(name = "user_id", index = true)
    private int userId;

    @ColumnInfo(name = "location_id", index = true)
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
    public void setUserId(int userId) { this.userId = userId; }

    public int getLocationId() { return locationId; }
    public void setLocationId(int locationId) { this.locationId = locationId; }

    @NonNull
    @Override
    @Ignore
    public String toString() {
        return "UserFavoriteLocation{" +
            "userId: " + userId +
            ", locationId: " + locationId +
            '}';
    }
}
