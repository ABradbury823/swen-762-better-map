package com.example.swen766_bettermaps.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.swen766_bettermaps.data.db.types.UserRole;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String email;
    private UserRole role;

    public User(String username, String email, UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public int getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserRole getRole() { return role; }
    public void setUserRole(UserRole role) { this.role = role; }
}
