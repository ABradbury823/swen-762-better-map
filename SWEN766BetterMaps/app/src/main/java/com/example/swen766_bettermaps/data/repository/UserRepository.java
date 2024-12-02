package com.example.swen766_bettermaps.data.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.UserDAO;
import com.example.swen766_bettermaps.data.db.entities.User;
import com.example.swen766_bettermaps.data.db.entities.UserFavoriteLocation;
import com.example.swen766_bettermaps.data.db.entities.joins.UserWithFavoriteLocations;
import com.example.swen766_bettermaps.data.repository.exceptions.UserException;
import com.example.swen766_bettermaps.data.repository.exceptions.UserFavoriteLocationException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private UserDAO userDAO;
    private ExecutorService executorService;

    public UserRepository(Application application) {
        BMDatabase db = BMDatabase.getInstance(application.getApplicationContext());
        userDAO = db.userDAO();

        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Inserts a new user into the database.
     * @param user The new user.
     * @throws UserException If the user id, name, or email already matches an existing user.
     */
    public void insert(User user) throws UserException {
        executorService.submit(() -> {
            if(userDAO.getUserById(user.getId()) != null) {
                throw new UserException("User with id " +
                    user.getId() + " already exists.");
            } else if (userDAO.getUserByName(user.getUsername()) != null) {
                throw new UserException("User with username " +
                    user.getUsername() + " already exists.");
            } else if (userDAO.getUserByEmail(user.getEmail()) != null) {
                throw new UserException("User with email " +
                    user.getEmail() + " already exists.");
            }
            userDAO.insert(user);
        });
    }

    /**
     * Inserts a new connection between a user and a location,
     * marking it as a favorite location.
     * @param userFavoriteLocation A join table containing the id of a user and a location.
     */
    public void insertFavoriteLocation(UserFavoriteLocation userFavoriteLocation)
    throws UserFavoriteLocationException {
        executorService.submit(() -> {
           if(userDAO.getUserFavoriteLocation(
               userFavoriteLocation.getUserId(), userFavoriteLocation.getLocationId()) == null) {
               userDAO.insertFavoriteLocation(userFavoriteLocation);
           } else {
               throw new UserFavoriteLocationException("User with id " +
                   userFavoriteLocation.getUserId() +
                   " already has a favorite location with id " +
                   userFavoriteLocation.getLocationId());
           }
        });
    }

    /**
     * Retrieves a list of all users in the database.
     * @param callback The DataCallback to determine how the retrieved User List should be used.
     */
    public void getAllUsers(final DataCallback<List<User>> callback) {
        executorService.submit(() -> {
            List<User> users = userDAO.getAllUsers();
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(users));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()). post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a single user by id.
     * @param callback The DataCallback to determine how the retrieved User should be used.
     */
    public void getUserById(final long userId, final DataCallback<User> callback)
    throws UserException{
        executorService.submit(() -> {
            User user = userDAO.getUserById(userId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(user));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()). post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a connection between a user and a location.
     * @param userId The id of the User.
     * @param locationId The id of the location
     * @param callback The DataCallback to determine how the
     *                 retrieved UserFavoriteLocation should be used.
     */
    public void getUserFavoriteLocation(final long userId, final long locationId,
                                         final DataCallback<UserFavoriteLocation> callback) {
        executorService.submit(() -> {
            UserFavoriteLocation ufl = userDAO.getUserFavoriteLocation(
                userId, locationId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(ufl));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()). post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a User and their favorite Locations.
     * @param userId The id of the User.
     * @param callback The DataCallback to determine how the
     *                 retrieved UserWithFavoriteLocations should be used.
     */
    public void getUserWithFavoriteLocations(final long userId,
                                             final DataCallback<UserWithFavoriteLocations> callback) {
        executorService.submit(() -> {
            UserWithFavoriteLocations u_wfl = userDAO.getUserWithFavoriteLocations(userId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(u_wfl));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()). post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Updates a user with new information.
     * @param user The user with updated information.
     * @throws UserException if the user id does not exist, or the new username/email already
     * belong to another user.
     */
    public void update(User user) throws UserException {
        executorService.submit(() -> {
            if(userDAO.getUserById(user.getId()) == null) {
                throw new UserException("User with id " +
                    user.getId() + " does not exist.");
            } else if (userDAO.getUserByName(user.getUsername()) != null) {
                throw new UserException("User with username " +
                    user.getUsername() + " already exists.");
            } else if (userDAO.getUserByEmail(user.getEmail()) != null) {
                throw new UserException("User with email " +
                    user.getEmail() + " already exists.");
            }
            userDAO.update(user);
        });
    }

    /**
     * Removes a user from the database.
     * @param user The user to delete.
     * @throws UserException if the user id does not exist.
     */
    public void delete(User user) throws UserException {
        executorService.submit(() -> {
            if(userDAO.getUserById(user.getId()) == null) {
                throw new UserException("User with id " +
                    user.getId() + " does not exist.");
            }
            userDAO.delete(user);
        });
    }

    /**
     * Deletes a favorite location from a user.
     * @param ufl The connection between user and location.
     * @throws UserFavoriteLocationException if user id or location id do not share an
     * entry in the favorites table.
     */
    public void deleteFavoriteLocation(UserFavoriteLocation ufl)
    throws UserFavoriteLocationException{
        executorService.submit(() -> {
           if(userDAO.getUserFavoriteLocation(ufl.getUserId(), ufl.getLocationId()) == null) {
               throw new UserFavoriteLocationException("User with id " +
                   ufl.getUserId() + " does not have a favorite location with id " +
                   ufl.getLocationId());
           }
           userDAO.deleteFavoriteLocation(ufl);
        });
    }

}
