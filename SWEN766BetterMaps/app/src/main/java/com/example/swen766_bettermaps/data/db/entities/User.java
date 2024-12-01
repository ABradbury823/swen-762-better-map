package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.example.swen766_bettermaps.data.db.types.UserRoleConverter;
import com.example.swen766_bettermaps.data.db.types.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a user of BetterMaps.
 */
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    @TypeConverters(UserRoleConverter.class)
    private UserRole role;

    @Ignore
    @Relation(
        parentColumn = "id",                        // primary key in user
        entityColumn = "user_id",                   // foreign key in join table
        associateBy = @Junction(UserFavoriteLocation.class)    // join table
    )
    private List<Location> favoriteLocations;   // will be filled by join table

    // Default constructor for Room functionality
    public User() {
        this.username = "";
        this.email = "";
        this.role = UserRole.NONE;
        initLists();
    }

    /**
     * Constructs a User.
     * @param username the user's username.
     * @param email the user's email.
     * @param role the user's role (STUDENT, FACULTY, ADMIN).
     */
    @Ignore
    public User(@NonNull String username, @NonNull String email, @NonNull UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
        initLists();
    }

    @Ignore
    private void initLists() {
        this.favoriteLocations = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getUsername() { return username; }
    public void setUsername(@NonNull String username) { this.username = username; }

    @NonNull
    public String getEmail() { return email; }
    public void setEmail(@NonNull String email) { this.email = email; }

    @NonNull
    public UserRole getRole() { return role; }
    public void setRole(@NonNull UserRole role) { this.role = role; }

    public List<Location> getFavoriteLocations() { return favoriteLocations; }
    public void setFavoriteLocations(List<Location> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    /**
     * Adds a location to the user's favorite locations.
     * @param location The location to add.
     */
    public void addFavoriteLocation(Location location) {
        if(favoriteLocations == null) {
            favoriteLocations = new ArrayList<>();
        }
        favoriteLocations.add(location);
    }

    /**
     * Removes a location from the user's favorite locations.
     * @param location The location to remove.
     */
    public void removeFavoriteLocation(Location location) {
        if(favoriteLocations == null) return;

        favoriteLocations.remove(location);
    }

    @NonNull
    @Override
    @Ignore
    public String toString() {
        return "User{" +
            "id: " + id +
            ", username: '" + username + '\'' +
            ", email: '" + email + '\'' +
            ", role: " + role +
            '}';
    }
}
