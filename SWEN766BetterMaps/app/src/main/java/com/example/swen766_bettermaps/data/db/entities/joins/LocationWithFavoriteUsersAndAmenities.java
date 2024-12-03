package com.example.swen766_bettermaps.data.db.entities.joins;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;

import java.util.List;

public class LocationWithFavoriteUsersAndAmenities {
    @Embedded
    public Location location;

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = @Junction(
            value = UserFavoriteLocation.class,
            parentColumn = "location_id",
            entityColumn = "user_id"
        )
    )
    public List<User> favoriteUsers;

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = @Junction(
            value = LocationAmenity.class,
            parentColumn = "location_id",
            entityColumn = "amenity_id"
        )
    )
    public List<Amenity> amenities;
}
