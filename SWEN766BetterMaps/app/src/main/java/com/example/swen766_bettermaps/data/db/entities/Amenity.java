package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Entity representing an amenity in Better Maps.
 */
@Entity(tableName = "amenities")
public class Amenity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    // default constructor for Room functionality
    public Amenity(){
        this.name = "";
        this.description = "";
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
    }

    /**
     * Constructs an amenity with a default description.
     * @param name The name of the amenity.
     */
    @Ignore
    public Amenity(@NonNull String name) {
        this(name, "No description available");
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }

    @NonNull
    public String getDescription() { return description; }
    public void setDescription(@NonNull String description) { this.description = description; }

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
