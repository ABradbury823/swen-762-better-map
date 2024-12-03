package com.example.swen766_bettermaps.data.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.LocationDAO;
import com.example.swen766_bettermaps.data.db.entities.Location;
import com.example.swen766_bettermaps.data.db.entities.LocationAmenity;
import com.example.swen766_bettermaps.data.db.entities.joins.LocationWithFavoriteUsersAndAmenities;
import com.example.swen766_bettermaps.data.repository.exceptions.LocationAmenityException;
import com.example.swen766_bettermaps.data.repository.exceptions.LocationException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocationRepository {
    private LocationDAO locationDAO;
    private ExecutorService executorService;

    public LocationRepository(Application application) {
        BMDatabase db = BMDatabase.getInstance(application.getApplicationContext());
        locationDAO = db.locationDAO();

        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Adds a new location to the database.
     * @param location The new location.
     * @throws LocationException If the location id already belongs to a location.
     */
    public void insert(Location location) throws LocationException {
        executorService.submit(() -> {
            if(locationDAO.getLocationById(location.getId()) != null) {
                throw new LocationException("Location with id " +
                    location.getId() + " already exists");
            }
            locationDAO.insert(location);
        });
    }

    /**
     * Inserts a new connection between a location and an amenity,
     * marking the location as providing the amenity
     * @param locationAmenity The connection containing the id of a location and amenity.
     * @throws LocationAmenityException If a connection already exists between the
     * id of the location and the id of the amenity.
     */
    public void insertLocationAmenity(LocationAmenity locationAmenity)
    throws LocationAmenityException {
        executorService.submit(() -> {
            if(locationDAO.getLocationAmenity(
                locationAmenity.getLocationId(),
                locationAmenity.getAmenityId()
            ) == null) {
                insertLocationAmenity(locationAmenity);
            }
            else {
                throw new LocationAmenityException("Location with id " +
                    locationAmenity.getLocationId() +
                    " already has an amenity with id " +
                    locationAmenity.getAmenityId());
            }
        });
    }

    /**
     * Retrieves all locations in the database
     * @param callback The DataCallback to determine how the retrieved List of Locations should be used.
     */
    public void getAllLocations(final DataCallback<List<Location>> callback) {
        executorService.submit(() -> {
           List<Location> locations = locationDAO.getAllLocations();
           try {
               new Handler(Looper.getMainLooper()).post(
                   () -> callback.onDataReceived(locations));
           } catch (Exception e) {
               new Handler(Looper.getMainLooper()).post(
                   () -> callback.onError(e));
           }
        });
    }

    /**
     * Retrieves a single location by id.
     * @param locationId The id of the location.
     * @param callback The DataCallback to determine how the retrieved Location should be used.
     */
    public void getLocationById(
        final long locationId,
        final DataCallback<Location> callback) {
        executorService.submit(() -> {
            Location location = locationDAO.getLocationById(locationId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(location));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a connection between a location and an amenity.
     * @param locationId The id of the location.
     * @param amenityId The id of the amenity.
     * @param callback The DataCallback to determine how the retrieved
     *                 LocationAmenity should be used.
     */
    public void getLocationAmenity(
        final long locationId,
        final long amenityId,
        final DataCallback<LocationAmenity> callback) {
        executorService.submit(() -> {
            LocationAmenity locationAmenity =
                locationDAO.getLocationAmenity(locationId, amenityId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(locationAmenity));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a Location, Users who have this Location as a Favorite,
     * and the Location's Amenities.
     * @param locationId The id of the location.
     * @param callback The DataCallback to determine how the retrieved Location should be used.
     */
    public void getLocationWithFavoriteUsersAndAmenities(
        final long locationId,
        final DataCallback<LocationWithFavoriteUsersAndAmenities> callback) {
        executorService.submit(() -> {
            LocationWithFavoriteUsersAndAmenities lwf_uaa
                = locationDAO.getLocationWithFavoriteUsersAndAmenities(locationId);
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(lwf_uaa));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onError(e));
            }
        });
    }

    /**
     * Updates a location with new information.
     * @param location The location with updated info.
     * @throws LocationException If the location id does not exist.
     */
    public void update(Location location) throws LocationException {
        executorService.submit(() -> {
           if(locationDAO.getLocationById(location.getId()) == null) {
               throw new LocationException("Location with id " +
                   location.getId() + " does not exist");
           }
           locationDAO.update(location);
        });
    }

    /**
     * Removes a location from the database.
     * @param location The location with updated info.
     * @throws LocationException If the location id does not exist.
     */
    public void delete(Location location) throws LocationException {
        executorService.submit(() -> {
            if(locationDAO.getLocationById(location.getId()) == null) {
                throw new LocationException("Location with id " +
                    location.getId() + " does not exist");
            }
            locationDAO.delete(location);
        });
    }

    /**
     * Removes an amenity from a location.
     * @param la The connection between location and amenity.
     * @throws LocationException if location id or amenity id do not share an
     * entry in the favorites table.
     */
    public void deleteLocationAmenity(LocationAmenity la)
    throws LocationAmenityException {
        executorService.submit(() -> {
            if(locationDAO.getLocationAmenity(
                la.getLocationId(), la.getAmenityId()) == null) {
                throw new LocationAmenityException("Location with id " +
                    la.getLocationId() +
                    " does not have an amenity with the id " +
                    la.getAmenityId()
                );
            }
            locationDAO.deleteLocationAmenity(la);
        });
    }

}
