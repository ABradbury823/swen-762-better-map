package com.example.swen766_bettermaps.data.db.entities.joins;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;

import java.util.List;

public class UserWithFavoriteLocations {
    @Embedded
    public User user;

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = @Junction(
            value = UserFavoriteLocation.class,
            parentColumn = "user_id",
            entityColumn = "location_id"

        )
    )
    public List<Location> favoriteLocations;
}
