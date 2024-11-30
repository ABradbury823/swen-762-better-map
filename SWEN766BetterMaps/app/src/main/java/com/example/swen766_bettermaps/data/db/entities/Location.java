package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.example.swen766_bettermaps.data.db.types.Coordinate;
import com.example.swen766_bettermaps.data.db.types.CoordinateConverter;

import java.util.ArrayList;
import java.util.List;

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

    @Ignore
    @Relation(
        parentColumn = "id",                            // primary key in location
        entityColumn = "location_id",                   // foreign key in join table
        associateBy = @Junction(UserFavoriteLocation.class)    // join table
    )
    private List<User> favoriteUsers;    // will be populated by join table

    @Ignore
    @Relation(
        parentColumn = "id",
        entityColumn = "location_id",
        associateBy = @Junction(LocationAmenities.class)
    )
    private List<Amenity> amenities;

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
        this(name,
            "No description available",
            "No address available",
            new Coordinate());
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

    public List<User> getFavoriteUsers() { return favoriteUsers; }
    public void setFavoriteUsers(List<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    /**
     * Adds a user to this location's favorite users.
     * @param user The user to add.
     */
    public void addFavoriteUser(User user) {
        if(favoriteUsers == null) {
            favoriteUsers = new ArrayList<>();
        }
        favoriteUsers.add(user);
    }

    /**
     * Removes a user from this location's favorite users.
     * @param user The user to remove.
     */
    public void removeFavoriteUser(User user) {
        if(favoriteUsers == null) return;
        favoriteUsers.remove(user);
    }

    public List<Amenity> getAmenities() { return amenities; }
    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    /**
     * Adds an amenity to this location's amenities.
     * @param amenity The amenity to add.
     */
    public void addAmenity(Amenity amenity) {
        if(amenities == null) {
            amenities = new ArrayList<>();
        }
        amenities.add(amenity);
    }

    /**
     * Removes an amenity from this location's amenities.
     * @param amenity The amenity to remove.
     */
    public void removeAmenity(Amenity amenity) {
        if(amenities == null) return;
        amenities.remove(amenity);
    }
}
