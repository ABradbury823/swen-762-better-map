package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.swen766_bettermaps.data.db.types.Coordinate;
import com.example.swen766_bettermaps.data.db.types.CoordinateConverter;

/**
 * Entity representing a location in Better Maps.
 */
@Entity(tableName = "locations")
public class Location {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String address;

    @NonNull
    @TypeConverters(CoordinateConverter.class)
    private Coordinate coordinates;

    /**
     * Constructs a Location.
     * @param name The location's name.
     * @param description A description of the location.
     * @param address The location's address.
     * @param coordinates The location's coordinates.
     */
    public Location(@NonNull String name,
                    @NonNull String description,
                    @NonNull String address,
                    @NonNull Coordinate coordinates) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.coordinates = coordinates;
    }

    /**
     * Constructs a Location with default values for description, address, and coordinates.
     * <br>&emsp;Description and address are empty by default.
     * <br>&emsp;Coordinates are set to (0.0, 0.0) by default.
     * @param name The location's name.
     */
    public Location(@NonNull String name) {
        this(name, "", "", new Coordinate());
    }

    public int getId() { return id; }

    @NonNull
    public String getName() { return name; }

    public void setName(@NonNull String name) { this.name = name; }

    @NonNull
    public String getDescription() { return description; }

    public void setDescription(@NonNull String description) { this.description = description; }

    @NonNull
    public String getAddress() { return address; }

    public void setAddress(@NonNull String address) { this.address = address; }

    @NonNull
    public Coordinate getCoordinates() { return coordinates; }

    public void setCoordinates(@NonNull Coordinate coordinates) { this.coordinates = coordinates; }
    public void setCoordinates(float latitude, float longitude) {
        this.coordinates = new Coordinate(latitude, longitude);
    }
}
