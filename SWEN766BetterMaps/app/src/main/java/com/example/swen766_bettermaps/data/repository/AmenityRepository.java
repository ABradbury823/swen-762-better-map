package com.example.swen766_bettermaps.data.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.example.swen766_bettermaps.data.db.BMDatabase;
import com.example.swen766_bettermaps.data.db.daos.AmenityDAO;
import com.example.swen766_bettermaps.data.db.entities.Amenity;
import com.example.swen766_bettermaps.data.db.entities.joins.AmenityWithIncludedLocations;
import com.example.swen766_bettermaps.data.repository.exceptions.AmenityException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmenityRepository {
    private final AmenityDAO amenityDAO;
    private final ExecutorService executorService;

    public AmenityRepository(Application application) {
        BMDatabase db = BMDatabase.getInstance(application.getApplicationContext());
        amenityDAO = db.amenityDAO();

        // single thread executor for sequential tasks
        executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Inserts a new amenity into the database.
     * @param amenity The amenity to add.
     * @throws AmenityException An amenity with a matching id already exists.
     */
    public void insert(Amenity amenity) throws AmenityException {
        executorService.submit(() -> {
            if(amenityDAO.getAmenityById(amenity.getId()) == null) {
                amenityDAO.insert(amenity);
            } else {
                throw new AmenityException("Amenity with id " +
                    amenity.getId() + " already exists");
            }
        });
    }

    /**
     * Retrieves a list of all amenities in the database.
     * @param callback The DataCallback to determine how the retrieved Amenity List should be used.
     */
    public void getAllAmenities(final DataCallback<List<Amenity>> callback) {
        executorService.submit(() -> {
            List<Amenity> amenities = amenityDAO.getAllAmenities();
            // return data to main thread via callback
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(amenities));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves a single amenity by id.
     * @param amenityId The id of the Amenity.
     * @param callback The DataCallback to determine how the retrieved Amenity should be used.
     */
    public void getAmenityById(final long amenityId, final DataCallback<Amenity> callback) {
        executorService.submit(() -> {
            Amenity amenity = amenityDAO.getAmenityById(amenityId);
            // return data to main thread via callback
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(amenity));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        });
    }

    /**
     * Retrieves an Amenity with a List of Locations where it is included.
     * @param amenityId The id of the Amenity.
     * @param callback The DataCallback to determine how the
     *                 retrieved AmenityWithIncludedLocations should be used.
     */
    public void getAmenityWithIncludedLocations
        (final long amenityId, final DataCallback<AmenityWithIncludedLocations> callback) {
        executorService.submit(() -> {
            AmenityWithIncludedLocations amenityWithIncludedLocations =
                amenityDAO.getAmenityWithIncludedLocations(amenityId);
            // return data to main thread via callback
            try {
                new Handler(Looper.getMainLooper()).post(
                    () -> callback.onDataReceived(amenityWithIncludedLocations));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        });
    }

    /**
     * Update an amenity in the database
     * @param amenity The amenity to update.
     * @throws AmenityException The id of the Amenity does not match an existing Amenity.
     */
    public void update(Amenity amenity) throws AmenityException {
        executorService.submit(() -> {
           if(amenityDAO.getAmenityById(amenity.getId()) != null) {
               amenityDAO.update(amenity);
           } else {
               throw new AmenityException("Amenity with id " +
                   amenity.getId() + " does not exist");
           }
        });
    }

    /**
     * Removes an Amenity from the database.
     * @param amenity The Amenity to remove.
     * @throws AmenityException The if og the Amenity does not match an existing Amenity.
     */
    public void delete(Amenity amenity) throws AmenityException {
        executorService.submit(() -> {
           if(amenityDAO.getAmenityById(amenity.getId()) != null) {
               amenityDAO.delete(amenity);
           } else {
               throw new AmenityException("Amenity with id " +
                   amenity.getId() + " does not exist");
           }
        });
    }

}
