package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.swen766_bettermaps.data.db.types.UserRoleConverter;
import com.example.swen766_bettermaps.data.db.types.UserRole;

/**
 * Entity representing a user of BetterMaps.
 */
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    @TypeConverters(UserRoleConverter.class)
    private UserRole role;

    // Default constructor for Room functionality
    public User() {
        this.username = "";
        this.email = "";
        this.role = UserRole.NONE;
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
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @NonNull
    public String getUsername() { return username; }
    public void setUsername(@NonNull String username) { this.username = username; }

    @NonNull
    public String getEmail() { return email; }
    public void setEmail(@NonNull String email) { this.email = email; }

    @NonNull
    public UserRole getRole() { return role; }
    public void setRole(@NonNull UserRole role) { this.role = role; }

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
