package com.example.swen766_bettermaps.data.db.entities.joins;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;

import java.util.List;

public class AmenityWithIncludedLocations {
    @Embedded
    public Amenity amenity;

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = @Junction(
            value = LocationAmenity.class,
            parentColumn = "amenity_id",
            entityColumn = "location_id"
        )
    )
    public List<Location> includedLocations;
}
