package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an amenity in Better Maps.
 */
@Entity(tableName = "amenities")
public class Amenity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @Ignore
    @Relation(
        parentColumn = "id",
        entityColumn = "amenity_id",
        associateBy = @Junction(LocationAmenities.class)
    )
    private List<Location> includedLocations;

    public Amenity(@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
    }

    public Amenity(@NonNull String name) {
        this(name, "No description available");
    }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }

    @NonNull
    public String getDescription() { return description; }
    public void setDescription(@NonNull String description) { this.description = description; }

    public List<Location> getIncludedLocations() { return includedLocations; }
    public void setIncludedLocations(List<Location> includedLocations) {
        this.includedLocations = includedLocations;
    }

    /**
     * Adds a location to this amenity's included locations.
     * @param location The location to remove.
     */
    public void addIncludedLocation(Location location) {
        if(includedLocations == null) {
            includedLocations = new ArrayList<>();
        }
        includedLocations.add(location);
    }

    /**
     * Removes a location from this amenity's included locations.
     * @param location The location to remove.
     */
    public void removeIncludedLocation(Location location) {
        if(includedLocations == null) return;

        includedLocations.remove(location);
    }
}
