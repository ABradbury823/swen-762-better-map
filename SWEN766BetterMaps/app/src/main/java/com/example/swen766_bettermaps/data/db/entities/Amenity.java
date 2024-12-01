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
        associateBy = @Junction(LocationAmenity.class)
    )
    private List<Location> includedLocations;

    // default constructor for Room functionality
    public Amenity(){
        this.name = "";
        this.description = "";
        initLists();
    }

    /***
     * Constructs an Amenity.
     * @param name The name of the amenity.
     * @param description A description of the amenity.
     */
    @Ignore
    public Amenity(@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
        initLists();
    }

    /**
     * Constructs an amenity with a default description.
     * @param name The name of the amenity.
     */
    @Ignore
    public Amenity(@NonNull String name) {
        this(name, "No description available");
    }

    @Ignore
    private void initLists() {
        this.includedLocations = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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
    @Ignore
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
    @Ignore
    public void removeIncludedLocation(Location location) {
        if(includedLocations == null) return;

        includedLocations.remove(location);
    }

    @NonNull
    @Override
    @Ignore
    public String toString() {
        return "Amenity{" +
            "id: " + id +
            ", name: '" + name + '\'' +
            ", description: '" + description + '\'' +
            '}';
    }
}
