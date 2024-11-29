package com.example.swen766_bettermaps.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.swen766_bettermaps.data.db.types.UserRole;

/**
 * Entity representing a user of BetterMaps.
 */
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "role")
    private UserRole role;


    /**
     * Constructs a User.
     * @param username the user's username.
     * @param email the user's email.
     * @param role the user's role (STUDENT, FACULTY, ADMIN).
     */
    public User(@NonNull String username, @NonNull String email, @NonNull UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public int getId() { return id; }

    @NonNull
    public String getUsername() { return username; }
    public void setUsername(@NonNull String username) { this.username = username; }

    @NonNull
    public String getEmail() { return email; }
    public void setEmail(@NonNull String email) { this.email = email; }

    @NonNull
    public UserRole getRole() { return role; }
    public void setUserRole(@NonNull UserRole role) { this.role = role; }
}
