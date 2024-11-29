package com.example.swen766_bettermaps.data.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swen766_bettermaps.data.db.entities.User;

import java.util.List;

/**
 * A Data Access Object containing methods for accessing the Users table.
 */
@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    User getUserById(int userId);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
