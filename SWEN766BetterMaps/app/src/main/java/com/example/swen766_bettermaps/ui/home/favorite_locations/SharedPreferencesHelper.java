package com.example.swen766_bettermaps.ui.home.favorite_locations;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class SharedPreferencesHelper {

    private static final String FAVORITES_KEY = "favorite_locations";

    private SharedPreferences sharedPreferences;

    // Constructor
    public SharedPreferencesHelper(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // Save a set of favorite locations
    public void saveFavorites(Set<String> favorites) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(FAVORITES_KEY, favorites);
        editor.apply();  // Use apply() for async saving
    }

    // Get the favorite locations (returns a Set of Strings)
    public Set<String> getFavorites() {
        return sharedPreferences.getStringSet(FAVORITES_KEY, null);  // Returns null if no favorites saved
    }

    // Add a single favorite location
    public void addFavorite(String location) {
        Set<String> favorites = getFavorites();
        if (favorites != null) {
            favorites.add(location);
            saveFavorites(favorites);
        }
    }

    // Remove a favorite location
    public void removeFavorite(String location) {
        Set<String> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(location);
            saveFavorites(favorites);
        }
    }

    // Clear all favorite locations
    public void clearFavorites() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(FAVORITES_KEY);  // Remove the favorites from SharedPreferences
        editor.apply();
    }
}

